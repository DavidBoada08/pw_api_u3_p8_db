package uce.edu.web.api.controller;
 
import java.util.List;
 
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.Operation;


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
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.service.IEstudianteService;
 
@Path("/estudiantes")
public class EstudianteController {
 
    @Inject
    private IEstudianteService estudianteService;
 
    @GET
    @Path("/{id}")
    public Response consultarEstudiantePorId(@PathParam("id") Integer id) {

        return Response.status(227).entity( this.estudianteService.buscarPorId(id)).build();
    }
 
 
    //?genero=F&provincia=pichincha
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Consultar Estudiantes",
        description = "Este endpoint obtiene una lista de todos los estudiantes"
    )
    public Response consultarEstudiantes(@QueryParam("genero")  String genero, @QueryParam("provincia") String provincia) {
        System.out.println(provincia);
        return Response.status(Response.Status.OK).entity(this.estudianteService.buscarTodos(genero)).build();
 
        
    }
 
    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Guardar Estudiante",
        description = "Guarda un nuevo estudiante en el sistema"
    )
    public void guardar(@RequestBody Estudiante estudiante) {
       this.estudianteService.guardar(estudiante);
    }
 
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public void actualizar(@RequestBody Estudiante estudiante, @PathParam("id") Integer id) {
        estudiante.setId(id);
       this.estudianteService.actualizarPorId(estudiante);
    }
 
    @PATCH
    @Path("/{id}")
    public void actualizarParcial(@RequestBody Estudiante estudiante, @PathParam("id") Integer id) {
        estudiante.setId(id);
        Estudiante e = this.estudianteService.buscarPorId(id);
        if(estudiante.getNombre() != null) {
            e.setNombre(estudiante.getNombre());
        }
        if(estudiante.getApellido() != null) {
            e.setApellido(estudiante.getApellido());
        }
        if(estudiante.getFechaNacimiento() != null) {
            e.setFechaNacimiento(estudiante.getFechaNacimiento());
        }
        this.estudianteService.actualizarParcialPorId(e);
    }
 
    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Integer id) {
        this.estudianteService.eliminarPorId(id);
    }
}