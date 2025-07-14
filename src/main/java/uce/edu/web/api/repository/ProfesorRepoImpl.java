package uce.edu.web.api.repository;

import java.util.List;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.modelo.Profesor;

@Transactional
@ApplicationScoped
public class ProfesorRepoImpl implements IProfesorRepo {
    
    @PersistenceContext
    private EntityManager entityManager;

    
    @Override
    public Profesor seleccionarPorId(Integer id) {
        return this.entityManager.find(Profesor.class, id);
    }

    @Override
    public List<Profesor> seleccionarTodos(String materia) {
        String jpql = "SELECT p FROM Profesor p";
        if (materia != null && !materia.isEmpty()) {
            jpql += " WHERE p.materia = :materia";
        }
        TypedQuery<Profesor> query = this.entityManager.createQuery(jpql, Profesor.class);
        if (materia != null && !materia.isEmpty()) {
            query.setParameter("materia", materia);
        }
        return query.getResultList();
    }
    
    @Override
    public void actualizarPorId(Profesor profesor) {
        this.entityManager.merge(profesor);
    }

    @Override
    public void actualizarParcialPorId(Profesor profesor) {
        Profesor existente = this.entityManager.find(Profesor.class, profesor.getId());
        if (existente != null) {
            if (profesor.getNombre() != null) existente.setNombre(profesor.getNombre());
            if (profesor.getApellido() != null) existente.setApellido(profesor.getApellido());
            if (profesor.getSueldo() != 0) existente.setSueldo(profesor.getSueldo());
            if (profesor.getTelefono() != null) existente.setTelefono(profesor.getTelefono());
            if (profesor.getMateria() != null) existente.setMateria(profesor.getMateria());
            this.entityManager.merge(existente);
        }
    }

    @Override
    public void borrarPorId(Integer id) {
        Profesor profesor = this.entityManager.find(Profesor.class, id);
        if (profesor != null) {
            this.entityManager.remove(profesor);
        }
    }

    @Override
    public void insertar(Profesor profesor) {
        this.entityManager.persist(profesor);
    }
}
