/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.services;

import com.example.PersistenceManager;
import com.example.models.Proyecto;
import com.example.models.ProyectoAsignado;
import com.example.models.ProyectoAsignadoDTO;
import com.example.models.ProyectoDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.EntityManager;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONObject;

@Path("/proyecto")
@Produces(MediaType.APPLICATION_JSON)
public class Proyectoservice {
    
    @PersistenceContext(unitName = "Crow")
     EntityManager entityManager;

    @PostConstruct
    public void init() {
        try {
        entityManager = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
    
    @PUT
    @Path("/donar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProyecto(@PathParam("id") Long id, ProyectoDTO proyecto) {
        JSONObject rta = new JSONObject();
        Proyecto proyectoTmp = entityManager.find(Proyecto.class, id);
        proyectoTmp.setDonante(proyecto.getDonante());
        proyectoTmp.setValorActual(proyectoTmp.getValorActual() + proyecto.getValorActual());
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(proyectoTmp);
            entityManager.persist(proyectoTmp);
            entityManager.getTransaction().commit();
            entityManager.refresh(proyectoTmp);
            rta.put("donador_id", proyectoTmp.getDonante());
            rta.put("valor_actual", proyectoTmp.getValorActual());
        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        proyectoTmp = null;
        } finally {
            entityManager.clear();
            entityManager.close();
        }
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(rta).build();
 }
    
    @PUT
    @Path("/estado/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response estado(@PathParam("id") Long id, ProyectoDTO proyecto) {
        JSONObject rta = new JSONObject();
        Proyecto proyectoTmp = entityManager.find(Proyecto.class, id);
        proyectoTmp.setEstado(proyecto.getEstado());
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(proyectoTmp);
            entityManager.persist(proyectoTmp);
            entityManager.getTransaction().commit();
            entityManager.refresh(proyectoTmp);
            rta.put("estado", proyectoTmp.getEstado());
        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        proyectoTmp = null;
        } finally {
            entityManager.clear();
            entityManager.close();
        }
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(rta).build();
    }
    
    @GET
    @Path("/verestado/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verestado(@PathParam("id") Long id){
//        Query q = entityManager.createQuery("select u from Emprendedor u order by u.nombre ASC");
//        List<Emprendedor> emprendedor = q.getResultList();
        TypedQuery<Proyecto>query =(TypedQuery<Proyecto>)
            entityManager.createQuery("SELECT c.nombre, c.estado FROM Proyecto c"+" WHERE c.id = :id");
            List<Proyecto>proyecto =query.setParameter("id", id).getResultList();        
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(proyecto).build();
    }
    
}
