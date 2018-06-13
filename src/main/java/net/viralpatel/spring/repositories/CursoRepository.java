package net.viralpatel.spring.repositories;

import net.viralpatel.spring.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CursoRepository  extends JpaRepository<Curso, Long> {

}


