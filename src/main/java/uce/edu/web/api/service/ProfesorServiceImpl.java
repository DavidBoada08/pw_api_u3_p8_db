package uce.edu.web.api.service;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.IProfesorRepo;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.mapper.ProfesorMapper;
import uce.edu.web.api.service.to.ProfesorTo;

@ApplicationScoped
public class ProfesorServiceImpl implements IProfesorService {

    @Inject
    private IProfesorRepo profesorRepo;

    @Override
    public ProfesorTo buscarPorIdTo(Integer id) {
        Profesor profesor = this.profesorRepo.seleccionarPorId(id);
        return ProfesorMapper.toTo(profesor);
    }

    @Override
    public List<ProfesorTo> buscarTodosTo(String materia) {
        List<Profesor> profesores = this.profesorRepo.seleccionarTodos(materia);
        List<ProfesorTo> profesoresTo = new ArrayList<>();
        for (Profesor profesor : profesores) {
            profesoresTo.add(ProfesorMapper.toTo(profesor));
        }
        return profesoresTo;
    }
    
    @Override
    public void actualizarPorIdTo(ProfesorTo profesorTo) {
        Profesor profesor = ProfesorMapper.toEntity(profesorTo);
        this.profesorRepo.actualizarPorId(profesor);
    }

    @Override
    public void actualizarParcialPorIdTo(ProfesorTo profesorTo) {
        Profesor profesor = ProfesorMapper.toEntity(profesorTo);
        this.profesorRepo.actualizarParcialPorId(profesor);
    }

    @Override
    public void eliminarPorId(Integer id) {
        this.profesorRepo.borrarPorId(id);
    }

    @Override
    public void guardarTo(ProfesorTo profesorTo) {
        Profesor profesor = ProfesorMapper.toEntity(profesorTo);
        this.profesorRepo.insertar(profesor);
    }
}
