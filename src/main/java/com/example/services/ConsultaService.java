/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.services;

import com.example.PersistenceManager;
import com.example.models.Donante;
import com.example.models.DonanteDTO;
import com.example.models.Emprendedor;
import com.example.models.EmprendedorDTO;
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
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONObject;

@Path("/consulta")
@Produces(MediaType.APPLICATION_JSON)
public class ConsultaService {
    
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
    
    @POST
    @Path("/agregarempre")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEmprendedor(EmprendedorDTO emprendedor) {
        JSONObject rta = new JSONObject();
        Emprendedor emprendedorTmp = new Emprendedor();
        emprendedorTmp.setClave(emprendedor.getClave());
        emprendedorTmp.setCedula(emprendedor.getCedula());
        emprendedorTmp.setCorreo(emprendedor.getCorreo());
        emprendedorTmp.setNombre(emprendedor.getNombre());
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(emprendedorTmp);
            entityManager.getTransaction().commit();
            entityManager.refresh(emprendedorTmp);
            rta.put("emprendedor_id", emprendedorTmp.getId());
        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        emprendedorTmp = null;
        } finally {
            entityManager.clear();
            entityManager.close();
        }
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(rta).build();
 }
    
    @POST
    @Path("/agregardona")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDonador(DonanteDTO donante) {
        JSONObject rta = new JSONObject();
        Donante donanteTmp = new Donante();
        donanteTmp.setClave(donante.getClave());
        donanteTmp.setCedula(donante.getCedula());
        donanteTmp.setCorreo(donante.getCorreo());
        donanteTmp.setNombre(donante.getNombre());
        donanteTmp.setTipoProyecto(donante.getTipoProyecto());
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(donanteTmp);
            entityManager.getTransaction().commit();
            entityManager.refresh(donanteTmp);
            rta.put("donador_id", donanteTmp.getId());
        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        donanteTmp = null;
        } finally {
            entityManager.clear();
            entityManager.close();
        }
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(rta).build();
 }
    
    @GET
    @Path("/emprendedor/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response veremprendedor(@PathParam("id") Long id){
//        Query q = entityManager.createQuery("select u from Emprendedor u order by u.nombre ASC");
//        List<Emprendedor> emprendedor = q.getResultList();
        TypedQuery<Emprendedor>query =(TypedQuery<Emprendedor>)
            entityManager.createQuery("SELECT c.nombre, c.correo, c.cedula FROM Emprendedor c"+" WHERE c.id = :id");
            List<Emprendedor>emprendedor =query.setParameter("id", id).getResultList();        
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(emprendedor).build();
    }
    
    @GET
    @Path("/donador/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@PathParam("id") Long id){
//        Query q = entityManager.createQuery("select u from Emprendedor u order by u.nombre ASC");
//        List<Emprendedor> emprendedor = q.getResultList();
        TypedQuery<Donante>query =(TypedQuery<Donante>)
            entityManager.createQuery("SELECT c.nombre, c.correo, c.cedula, c.tipoProyecto FROM Donante c"+" WHERE c.id = :id");
            List<Donante>donante =query.setParameter("id", id).getResultList();        
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(donante).build();
    }
    
    @POST
    @Path("/actualizaempre")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualEmprendedor(EmprendedorDTO emprendedor) 
    
    {
        JSONObject rta = new JSONObject();
        Emprendedor emprendedorTmp = new Emprendedor();
        emprendedorTmp.setClave(emprendedor.getClave());
        emprendedorTmp.setCedula(emprendedor.getCedula());
        emprendedorTmp.setCorreo(emprendedor.getCorreo());
        emprendedorTmp.setNombre(emprendedor.getNombre());
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(emprendedorTmp);
            entityManager.getTransaction().commit();
            entityManager.refresh(emprendedorTmp);
            rta.put("emprendedor_id", emprendedorTmp.getId());
        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        emprendedorTmp = null;
        } finally {
            entityManager.clear();
            entityManager.close();
        }
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(rta).build();
 }
}
