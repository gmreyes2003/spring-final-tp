package net.viralpatel.spring.services;

import net.viralpatel.spring.model.Estudiante;

import java.util.List;

/**
 * Created by emilio on 5/30/18.
 */
public interface EstudianteService {

    Estudiante getEstudiante(Long id);

    List<Estudiante> getAllEstudiante();

    Estudiante saveEstudiante(Estudiante estudiante);

    Estudiante updateEstudiante(Estudiante estudiante);

    void deleteEstudiante(Long id);

}
