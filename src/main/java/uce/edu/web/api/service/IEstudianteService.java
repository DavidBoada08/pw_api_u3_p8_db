package uce.edu.web.api.service;
 
import java.util.List;

import uce.edu.web.api.service.to.EstudianteTo;

 
public interface IEstudianteService {
 
    public EstudianteTo buscarPorIdTo(Integer id);
 
    public List<EstudianteTo> buscarTodosTo(String genero);
 
    public void actualizarPorIdTo(EstudianteTo estudianteTo);
 
    public void actualizarParcialPorIdTo(EstudianteTo estudianteTo);
 
    public void eliminarPorId(Integer id);
 
    public void guardarTo(EstudianteTo estudianteTo);
}
