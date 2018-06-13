package net.viralpatel.spring.controller;

import net.viralpatel.spring.model.Profesor;
import net.viralpatel.spring.services.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfesorRestController {

    @Autowired
    private ProfesorService profesorService;

    @PostMapping(value = "/profesores")
    public ResponseEntity createProfesor(@RequestBody Profesor profesor) {

        Profesor profesorGuardado = profesorService.createProfesor(profesor);

        return new ResponseEntity(profesorGuardado, HttpStatus.OK);
    }

    @GetMapping(value = "/profesores/{id}")
    public ResponseEntity getProfesor(@PathVariable("id") Long id) {
        Profesor profesor = profesorService.getProfesor(id);

        if (profesor == null) {
            throw new IllegalArgumentException(id.toString());
        }

        return new ResponseEntity(profesor, HttpStatus.OK);
    }
}
