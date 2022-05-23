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
import com.example.models.ProyectoAsignado;
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

@Path("/gestion")
@Produces(MediaType.APPLICATION_JSON)
public class GestionService {
    
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
    
    @GET
    @Path("/acumuladoauno/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response acumulado(@PathParam("id") Long id){
        TypedQuery<Proyecto>query =(TypedQuery<Proyecto>)
            entityManager.createQuery("SELECT c.ValorActual FROM Proyecto c"+" WHERE c.id = :id");
            List<Proyecto>acumulado =query.setParameter("id", id).getResultList();        
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(acumulado).build();
    }
    
    @GET
    @Path("/acumuladoatodos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allAcumulado(@PathParam("id") Long id){
        TypedQuery<ProyectoAsignado>query =(TypedQuery<ProyectoAsignado>)
            entityManager.createQuery("SELECT c.idproyecto, c.valor FROM ProyectoAsignado c"+" WHERE c.idpostulante = :id");
            List<ProyectoAsignado>allacumulado =query.setParameter("id", id).getResultList();        
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(allacumulado).build();
    }
    
    @GET
    @Path("/promediototal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response promedioTotal(){
        TypedQuery<Proyecto>query =(TypedQuery<Proyecto>)
            entityManager.createQuery("SELECT c.ValorActual FROM Proyecto c");
        List<Proyecto> promediototal = query.getResultList();
        int x = 0;
        int promedio = 0;
        for (Proyecto pro : promediototal) {
            promedio = promedio + pro.getValorActual();
            x++;
        }
        if(x!=0)
            promedio = promedio / x;
        
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(promedio).build();
    }
    
    @GET
    @Path("/promediorecaudo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response promedioRecaudo(){
        TypedQuery<ProyectoAsignado>query =(TypedQuery<ProyectoAsignado>)
            entityManager.createQuery("SELECT c.valor FROM ProyectoAsignado c");
        List<ProyectoAsignado> promediorecaudo = query.getResultList();
        int x = 0;
        int promedio = 0;
        for (ProyectoAsignado pro : promediorecaudo) {
            promedio = promedio + pro.getValor();
            x++;
        }
        if(x!=0)
            promedio = promedio / x;
        
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(promedio).build();
    }
    
}
