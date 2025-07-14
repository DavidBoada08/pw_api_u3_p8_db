package uce.edu.web.api.service;

import java.util.List;

import uce.edu.web.api.service.to.ProfesorTo;

public interface IProfesorService {
    public ProfesorTo buscarPorIdTo(Integer id);

    public List<ProfesorTo> buscarTodosTo(String materia);
    
    public void actualizarPorIdTo(ProfesorTo profesorTo);

    public void actualizarParcialPorIdTo(ProfesorTo profesorTo);

    public void eliminarPorId(Integer id);

    public void guardarTo(ProfesorTo profesorTo);
}
