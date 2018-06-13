package net.viralpatel.spring.model;

import javax.persistence.*;

@Entity
@Table(name = "cursoxestudiante")
public class CursoxEstudiante {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;



    public CursoxEstudiante() {
    }
}
