package uce.edu.web.api.service.to;

import jakarta.ws.rs.core.UriInfo;

public class ProfesorTo {

  
    private Integer id;

    private String nombre;

    private String apellido;

    private float sueldo;

    private String telefono;

    private String materia;
    
    private String uri;

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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
    
    public void buildURI(UriInfo uriInfo) {
        this.uri = uriInfo.getAbsolutePathBuilder().path(this.id.toString()).build().toString();
    }
}
