package uce.edu.web.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.repository.modelo.Hijo;
import uce.edu.web.api.service.IProfesorService;
import uce.edu.web.api.service.to.ProfesorTo;


@Path("/profesores")
public class ProfesorController extends BaseController {

    @Inject
    private IProfesorService profesorService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPorId(@PathParam("id")Integer id,@Context UriInfo uriInfo) {
        ProfesorTo prof= this.profesorService.buscarPorId(id, uriInfo);
        return Response.status(227).entity(prof).build(); 
    }

    //?genero=M&provincia=Pichincha  SOAP -> XML     RESTFul -> JSON
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarTodos(@QueryParam("genero") String genero, 
                        @QueryParam("provincia") String provincia) {
        System.out.println(provincia);
        return Response.status(Response.Status.OK).entity(this.profesorService.buscarTodos(genero)).build();
            
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Guardar profesores",description = "Esta capacidad permite guardar un profesor")
    //Puede tener o no tener el @RequestBody
    public Response guardar(Profesor profesor) {
        profesorService.guardar(profesor);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(@PathParam("id") Integer id, @RequestBody Profesor profesor) {
        profesor.setId(id);
        this.profesorService.actualizarPorId(profesor);
        this.profesorService.actualizarParcialPorId(profesor); 
        return Response.status(Response.Status.OK).build();
    }

    /*@PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarParcialPorId(@PathParam("id") Integer id, @RequestBody Profesor profesor) {
        profesor.setId(id);
        Profesor p=this.profesorService.buscarPorId(id);
        if(profesor.getNombre() != null) {
            p.setNombre(profesor.getNombre());
        }
        if(profesor.getApellido() != null) {
            p.setApellido(profesor.getApellido());
        }
        if(profesor.getFechaNacimiento() != null) {
            p.setFechaNacimiento(profesor.getFechaNacimiento());
        }

        this.profesorService.actualizarParcialPorId(p);
        return Response.status(Response.Status.OK).build();
    }*/
    
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response borrarPorId(@PathParam("id") Integer id) {
        this.profesorService.borrarPorId(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/{id}/hijos")
    public List<Hijo> obtenerHijosPorId(@PathParam("id") Integer id){

        Hijo h1 = new Hijo();
        Hijo h2 = new Hijo();
        h1.setNombre("Enrique");
        h2.setNombre("Jesus");

        List<Hijo> hijos = new ArrayList<>();
        hijos.add(h1);
        hijos.add(h2);
        return hijos;
    }
}
