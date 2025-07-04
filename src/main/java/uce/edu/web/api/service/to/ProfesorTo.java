package uce.edu.web.api.service.to;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.controller.ProfesorController;

public class ProfesorTo {

    
    private Integer id;
    
    private String nombre;
    
    private String apellido;
  
    private float sueldo;
   
    private String telefono;
  
    private String materia;

     public Map<String, String> _links=new HashMap<>();

    public ProfesorTo(Integer id, String nombre, String apellido, float sueldo, String telefono, String materia,UriInfo uriInfo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sueldo = sueldo;
        this.telefono = telefono;
        this.materia = materia;

        URI todosHijos= uriInfo
        .getBaseUriBuilder()
        .path(ProfesorController.class) 
        .path(ProfesorController.class, 
        "obtenerHijosPorId").build(id);

        _links.put("hijos", todosHijos.toString());

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }


    
}
