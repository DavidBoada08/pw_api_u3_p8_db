package uce.edu.web.api.controller;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.IProfesorService;

@Path("/profesores")
public class ProfesorController {
    @Inject
    private IProfesorService profesorService;

    @GET
    @Path("/{id}")
    public Profesor consultarPorId(@PathParam("id") Integer id) {
        return this.profesorService.buscarPorId(id);

    }
    @GET
    @Path("")
    public List<Profesor> consultarTodos() {
        return this.profesorService.buscarTodos();
    }

    @PUT
    @Path("/{id}")
    public void actualizar(Profesor profesor, @PathParam("id") Integer id) {
     this.profesorService.actualizarPorId(profesor);
    }

    @POST
    @Path("")
    public void guardar(Profesor profesor) {
        this.profesorService.guardar(profesor);
    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcial(Profesor profesor, @PathParam("id") Integer id)
    {
        profesor.setId(id);
        Profesor existente = this.profesorService.buscarPorId(id);
        if (existente.getNombre() == null) {
            existente.setNombre(profesor.getNombre());
        }
        if (existente.getApellido() != null) {
            existente.setApellido(profesor.getApellido());
        }
        if (existente.getSueldo() != 0.0f) {
            existente.setSueldo(profesor.getSueldo());
        }
        if (existente.getTelefono() != null) {
            existente.setTelefono(profesor.getTelefono());
        }
        if (existente.getMateria() != null) {
            existente.setMateria(profesor.getMateria());
        }
        this.profesorService.actualizarParcialPorId(existente);
    }

    @DELETE
    @Path("/{id}")
    public void borrarPorId(@PathParam("id") Integer id) {
        this.profesorService.borrarPorId(id);
    }



}
