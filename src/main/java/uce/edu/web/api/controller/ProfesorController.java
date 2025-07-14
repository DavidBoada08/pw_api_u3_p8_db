package uce.edu.web.api.controller;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
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
import uce.edu.web.api.service.IProfesorService;
import uce.edu.web.api.service.to.ProfesorTo;

@Path("/profesores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfesorController {

    @Inject
    private IProfesorService profesorService;

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarProfesorPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {

        ProfesorTo prof = this.profesorService.buscarPorIdTo(id);
        prof.buildURI(uriInfo);

        return Response.status(227).entity(prof).build();
    }

    // ?materia=matematicas
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Consultar Profesores", description = "Este endpoint obtiene una lista de todos los profesores")
    public Response consultarProfesores(@QueryParam("materia") String materia) {
        return Response.status(Response.Status.OK).entity(this.profesorService.buscarTodosTo(materia)).build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Guardar Profesor", description = "Guarda un nuevo profesor en el sistema")
    public Response guardar(@RequestBody ProfesorTo profesorTo) {
        this.profesorService.guardarTo(profesorTo);
        return Response.status(Response.Status.CREATED).entity("Profesor guardado exitosamente").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(@RequestBody ProfesorTo profesorTo, @PathParam("id") Integer id) {
        profesorTo.setId(id);
        this.profesorService.actualizarPorIdTo(profesorTo);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Actualizar Parcialmente Profesor", description = "Actualiza parcialmente un profesor existente")
    public Response actualizarParcial(@RequestBody ProfesorTo profesorTo, @PathParam("id") Integer id) {
        profesorTo.setId(id);
        this.profesorService.actualizarParcialPorIdTo(profesorTo);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Integer id) {
        this.profesorService.eliminarPorId(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
