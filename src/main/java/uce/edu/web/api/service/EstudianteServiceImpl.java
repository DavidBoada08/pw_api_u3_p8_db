package uce.edu.web.api.service;
 
import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.IEstudianteRepo;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.service.mapper.EstudianteMapper;
import uce.edu.web.api.service.to.EstudianteTo;
 
@ApplicationScoped
public class EstudianteServiceImpl implements IEstudianteService {
 
    @Inject
    private IEstudianteRepo estudianteRepo;
 
   
    
    @Override
    public EstudianteTo buscarPorIdTo(Integer id) {
        Estudiante estudiante = this.estudianteRepo.seleccionarPorId(id);
        return EstudianteMapper.toTo(estudiante);
    }
 
   
    
    @Override
    public List<EstudianteTo> buscarTodosTo(String genero) {
        List<Estudiante> estudiantes = this.estudianteRepo.seleccionarTodos(genero);
        List<EstudianteTo> estudiantesTo = new ArrayList<>();
        for (Estudiante estudiante : estudiantes) {
            estudiantesTo.add(EstudianteMapper.toTo(estudiante));
        }
        return estudiantesTo;
    }
 
  
    
    @Override
    public void actualizarPorIdTo(EstudianteTo estudianteTo) {
        Estudiante estudiante = EstudianteMapper.toEntity(estudianteTo);
        this.estudianteRepo.actualizarPorId(estudiante);
    }
 
    
    
    @Override
    public void actualizarParcialPorIdTo(EstudianteTo estudianteTo) {
        Estudiante estudiante = EstudianteMapper.toEntity(estudianteTo);
        this.estudianteRepo.actualizarParcialPorId(estudiante);
    }
 
    @Override
    public void eliminarPorId(Integer id) {
        this.estudianteRepo.borrarPorId(id);
    }
 
   
    
    @Override
    public void guardarTo(EstudianteTo estudianteTo) {
        Estudiante estudiante = EstudianteMapper.toEntity(estudianteTo);
        this.estudianteRepo.insertar(estudiante);
    }
}
