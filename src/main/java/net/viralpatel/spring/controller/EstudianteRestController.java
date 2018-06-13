package net.viralpatel.spring.controller;

import net.viralpatel.spring.exceptions.StudentNotFoundException;
import net.viralpatel.spring.model.Estudiante;
import net.viralpatel.spring.services.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EstudianteRestController {

	
	@Autowired
	private EstudianteService estudianteService;

	@GetMapping("/estudiantes")
	public List<Estudiante> getCustomers() {
		return estudianteService.getAllEstudiante();
	}

	@GetMapping("/estudiantes/{id}")
	public ResponseEntity getCustomer(@PathVariable("id") Long id) {

		Estudiante estudiante = estudianteService.getEstudiante(id);

		if (estudiante == null) {
			throw new StudentNotFoundException("Alumno no encontrado" + id.toString());
		}else{
			System.out.println("Estudiante " + estudiante.getFirstName());
		}

		return new ResponseEntity(estudiante, HttpStatus.OK);
	}

	@PostMapping(value = "/estudiantes")
	public ResponseEntity createEstudiante(@RequestBody Estudiante estudiante) {

		Estudiante estudianteGuardado = estudianteService.saveEstudiante(estudiante);

		return new ResponseEntity(estudianteGuardado, HttpStatus.OK);
	}

	@DeleteMapping("/estudiantes/{id}")
	public ResponseEntity deleteEstudiante(@PathVariable Long id) {

		estudianteService.deleteEstudiante(id);

		return new ResponseEntity(id, HttpStatus.OK);

	}

	@PutMapping("/estudiantes")
	public ResponseEntity updateEstudiante(@RequestBody Estudiante estudiante) {
		Estudiante estudianteActualizado = estudianteService.updateEstudiante(estudiante);
		return new ResponseEntity(estudianteActualizado, HttpStatus.OK);
	}



}