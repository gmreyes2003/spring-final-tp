package net.viralpatel.spring.services;

import net.viralpatel.spring.model.Profesor;
import net.viralpatel.spring.repositories.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Override
    public Profesor getProfesor(Long id) {
        return profesorRepository.findOne(id);
    }

    @Override
    public Profesor createProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }
}
