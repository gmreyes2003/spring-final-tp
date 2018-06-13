package net.viralpatel.spring.services;

import net.viralpatel.spring.model.Estudiante;
import net.viralpatel.spring.repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by emilio on 5/30/18.
 */
@Service
public class EstudianteServiceImpl implements EstudianteService{

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public Estudiante getEstudiante(Long id) {
        return estudianteRepository.findOne(id);
    }

    @Override
    public List<Estudiante> getAllEstudiante() {
        return estudianteRepository.findAll();
    }

    @Override
    public Estudiante saveEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public Estudiante updateEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public void deleteEstudiante(Long id) {
        estudianteRepository.delete(id);
    }
}
