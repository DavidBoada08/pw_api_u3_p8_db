package uce.edu.web.api.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.modelo.Hijo;

@Transactional
@ApplicationScoped
public class HijoRepoImpl implements IHijoRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Hijo> buscarPorEstudianteId(Integer id) {
        TypedQuery<Hijo> query = this.entityManager.createQuery(
            "SELECT h FROM Hijo h WHERE h.estudiante.id = :estudianteId", 
            Hijo.class
        );
        query.setParameter("estudianteId", id);
        return query.getResultList();
    }
}
