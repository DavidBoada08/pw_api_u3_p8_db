package uce.edu.web.api.controller;

import java.util.*;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.Operation;

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
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.repository.modelo.Hijo;
import uce.edu.web.api.service.IEstudianteService;
import uce.edu.web.api.service.to.EstudianteTo;

@Path("/estudiantes")
public class EstudianteController {

    @Inject
    private IEstudianteService estudianteService;

    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarEstudiantePorId(
            @PathParam("id") Integer id, @Context UriInfo uriInfo) {

        EstudianteTo estu = this.estudianteService.buscarPorId(id, uriInfo);

        return Response.status(227)
                .entity(estu)
                .build();
    }

    // ?genero=F&provincia=pichincha
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Consultar Estudiantes", description = "Este endpoint obtiene una lista de todos los estudiantes")
    public Response consultarEstudiantes(@QueryParam("genero") String genero,
            @QueryParam("provincia") String provincia) {
        System.out.println(provincia);
        return Response.status(Response.Status.OK).entity(this.estudianteService.buscarTodos(genero)).build();

    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Guardar Estudiante", description = "Guarda un nuevo estudiante en el sistema")
    public Response guardar(@RequestBody Estudiante estudiante) {
        this.estudianteService.guardar(estudiante);
        return Response.status(200).entity("Estudiante guardado exitosamente").build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response actualizar(@RequestBody Estudiante estudiante, @PathParam("id") Integer id) {
        estudiante.setId(id);
        this.estudianteService.actualizarPorId(estudiante);
        return Response.status(200).entity("Estudiante actualizado exitosamente").build();
    }

  /*   @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarParcial(@RequestBody Estudiante estudiante, @PathParam("id") Integer id) {
        estudiante.setId(id);
        Estudiante e = this.estudianteService.buscarPorId(id);
        if (estudiante.getNombre() != null) {
            e.setNombre(estudiante.getNombre());
        }
        if (estudiante.getApellido() != null) {
            e.setApellido(estudiante.getApellido());
        }
        if (estudiante.getFechaNacimiento() != null) {
            e.setFechaNacimiento(estudiante.getFechaNacimiento());
        }
        this.estudianteService.actualizarParcialPorId(e);
        return Response.status(200).entity("Estudiante actualizado parcialmente").build();
    } */

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Integer id) {
        this.estudianteService.eliminarPorId(id);
        return Response.status(200).entity("Estudiante eliminado exitosamente").build();
    }

    @GET
    @Path("/{id}/hijos")

    public List<Hijo> obtenerHijosPorId(@PathParam("id") Integer id) {

        Hijo h1 = new Hijo();
        h1.setNombre("Juanito");
        Hijo h2 = new Hijo();
        h2.setNombre("Anita");
        List<Hijo> hijos = new ArrayList<>();
        hijos.add(h1);
        hijos.add(h2);
        return hijos;

    }
}