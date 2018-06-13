package net.viralpatel.spring.repositories;

import net.viralpatel.spring.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

}