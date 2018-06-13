package net.viralpatel.spring.controller;

import net.viralpatel.spring.model.Curso;
import net.viralpatel.spring.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CursoRestController {

    @Autowired
    private CursoService cursoService;

    @PostMapping(value = "/cursos")
    public ResponseEntity createCurso(@RequestBody Curso curso) {

        Curso cursoGuardado = cursoService.createCurso(curso);

        return new ResponseEntity(cursoGuardado, HttpStatus.OK);
    }

    @GetMapping(value = "/cursos/{id}")
    public ResponseEntity getCurso(@PathVariable("id") Long id) {
        Curso curso = cursoService.getCurso(id);

        if (curso == null) {
            System.out.println("Curso no creado...");
            throw new IllegalArgumentException(id.toString());
        }

        return new ResponseEntity(curso, HttpStatus.OK);
    }
}
