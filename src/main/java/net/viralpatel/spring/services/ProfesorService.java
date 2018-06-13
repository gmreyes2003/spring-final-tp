package net.viralpatel.spring.services;

import net.viralpatel.spring.model.Profesor;

public interface ProfesorService {

    Profesor getProfesor(Long id);

    Profesor createProfesor(Profesor profesor);
}
