package uce.edu.web.api.repository.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "profesor")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prof_id")
    private Integer id;
    @Column(name = "prof_nombre")
    private String nombre;
    @Column(name = "prof_apellido")
    private String apellido;
    @Column(name = "prof_sueldo")
    private float sueldo;
    @Column(name = "prof_telefono")
    private String telefono;
    @Column(name = "prof_materia")
    private String materia;
    //getters y setters
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
