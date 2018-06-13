package net.viralpatel.spring.model;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;

@Entity
@EnableTransactionManagement
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @OneToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity=Profesor.class )
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;

    public Curso() {
    }

    public Curso(Long id, String description, Profesor profesor) {
        this.id = id;
        this.description = description;
        this.profesor = profesor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}
