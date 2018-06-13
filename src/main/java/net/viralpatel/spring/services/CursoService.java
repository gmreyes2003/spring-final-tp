package net.viralpatel.spring.services;

import net.viralpatel.spring.model.Curso;

public interface CursoService {

    Curso getCurso(Long id);

    Curso createCurso(Curso curso);
}
