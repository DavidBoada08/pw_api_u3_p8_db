package uce.edu.web.api.repository;
 
import java.util.List;
 
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.modelo.Estudiante;
 
 
@Transactional
@ApplicationScoped
public class EstudianteRepoImpl implements IEstudianteRepo {
 
    @PersistenceContext
    private EntityManager entityManager;
   
    @Override
    public Estudiante seleccionarPorId(Integer id) {
        return this.entityManager.find(Estudiante.class, id);
 
    }
 
    @Override
    public List<Estudiante> seleccionarTodos(String genero) {
        TypedQuery<Estudiante> myQuery = this.entityManager.createQuery("SELECT e FROM Estudiante e WHERE e.genero =: genero", Estudiante.class);
        myQuery.setParameter("genero", genero);
        return myQuery.getResultList();
    }
 
    @Override
    public void actualizarPorId(Estudiante estudiante) {
        this.entityManager.merge(estudiante);
    }
 
    @Override
    public void actualizarParcialPorId(Estudiante estudiante) {
        // Buscar el estudiante existente en la base de datos
        Estudiante estudianteExistente = this.entityManager.find(Estudiante.class, estudiante.getId());
        
        if (estudianteExistente != null) {
            // Solo actualizar los campos que no sean null
            if (estudiante.getNombre() != null) {
                estudianteExistente.setNombre(estudiante.getNombre());
            }
            if (estudiante.getApellido() != null) {
                estudianteExistente.setApellido(estudiante.getApellido());
            }
            if (estudiante.getFechaNacimiento() != null) {
                estudianteExistente.setFechaNacimiento(estudiante.getFechaNacimiento());
            }
            if (estudiante.getGenero() != null) {
                estudianteExistente.setGenero(estudiante.getGenero());
            }
            
            // Hacer merge del objeto existente con los cambios
            this.entityManager.merge(estudianteExistente);
        }
    }
 
    @Override
    public void borrarPorId(Integer id) {
        this.entityManager.remove(this.seleccionarPorId(id));
    }
 
    @Override
    public void insertar(Estudiante estudiante) {
        this.entityManager.persist(estudiante);
    }
}
 
 