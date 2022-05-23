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
import com.example.models.Proyecto;
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

@Path("/update")
@Produces(MediaType.APPLICATION_JSON)
public class UpdateService {
    
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
    @Path("/actualizaempre/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualEmprendedor(@PathParam("id") Long id,EmprendedorDTO emprendedor) 
    
    {              
            
        JSONObject rta = new JSONObject();
        Emprendedor emprendedorTmp = entityManager.find(Emprendedor.class, id);
        
        emprendedorTmp.setClave(emprendedor.getClave());
        emprendedorTmp.setCorreo(emprendedor.getCorreo());
        System.out.println(emprendedorTmp.getCorreo());
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(emprendedorTmp);
            entityManager.persist(emprendedorTmp);
            entityManager.getTransaction().commit();
            entityManager.refresh(emprendedorTmp);
            rta.put("emprendedor_correo", emprendedorTmp.getCorreo());
            rta.put("emprendedor_clave", emprendedorTmp.getClave());
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
    
    @PUT
    @Path("/actualizadona/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualDonador(@PathParam("id") Long id,DonanteDTO donador) 
    {        
        JSONObject rta = new JSONObject();
        Donante donadorTmp = entityManager.find(Donante.class, id);
        donadorTmp.setClave(donador.getClave());
        donadorTmp.setCorreo(donador.getCorreo());
        donadorTmp.setTipoProyecto(donador.getTipoProyecto());
        System.out.println(donadorTmp.getCorreo());
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(donadorTmp);
            entityManager.persist(donadorTmp);
            entityManager.getTransaction().commit();
            entityManager.refresh(donadorTmp);
            rta.put("donador_correo", donadorTmp.getCorreo());
            rta.put("donador_clave", donadorTmp.getClave());
            rta.put("tipo_proyecto", donadorTmp.getTipoProyecto());
        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        donadorTmp = null;
        } finally {
            entityManager.clear();
            entityManager.close();
        }
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(rta).build();
 }
    
    @PUT
    @Path("/actualizaproy/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProyecto(@PathParam("id") Long id, ProyectoDTO proyecto) {
        JSONObject rta = new JSONObject();
        Proyecto proyectoTmp = entityManager.find(Proyecto.class, id);
        proyectoTmp.setNombre(proyecto.getNombre());
        proyectoTmp.setDescripcion(proyecto.getDescripcion());
        proyectoTmp.setValorActual(proyecto.getValorActual());
        proyectoTmp.setTipoProyecto(proyecto.getTipoProyecto());
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(proyectoTmp);
            entityManager.persist(proyectoTmp);
            entityManager.getTransaction().commit();
            entityManager.refresh(proyectoTmp);
            rta.put("proyecto_nombre", proyectoTmp.getNombre());
            rta.put("proyecto_descripcion", proyectoTmp.getDescripcion());
            rta.put("proyecto_valor_actual", proyectoTmp.getValorActual());
            rta.put("proyecto_tipo_proyecto", proyectoTmp.getTipoProyecto());
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
    
    
}
