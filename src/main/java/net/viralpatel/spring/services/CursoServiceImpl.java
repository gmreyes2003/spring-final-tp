package net.viralpatel.spring.services;

import net.viralpatel.spring.model.Curso;
import net.viralpatel.spring.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public Curso getCurso(Long id) {
        return cursoRepository.findOne(id);
    }

    @Override
    public Curso createCurso(Curso curso) {
        System.out.println("Creando Curso...");
        return cursoRepository.save(curso);
    }
}
