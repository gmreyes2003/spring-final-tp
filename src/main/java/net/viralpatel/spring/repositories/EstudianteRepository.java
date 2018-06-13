package net.viralpatel.spring.repositories;

import net.viralpatel.spring.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by emilio on 5/30/18.
 */

@Transactional
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
}
