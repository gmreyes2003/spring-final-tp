package net.viralpatel.spring.controller;

import net.viralpatel.spring.model.Estudiante;
import net.viralpatel.spring.services.EstudianteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emilio on 6/4/18.
 */

public class EstudianteRestControllerMockTest {
    @Mock
    EstudianteService estudianteService;

    @Mock
    MessageSource message;

    @InjectMocks
    EstudianteRestController estudianteRestController;

    @Spy
    List<Estudiante> estudiantes = new ArrayList<Estudiante>();

    @Spy
    ModelMap model;

    @Mock
    BindingResult result;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        estudiantes = getEstudiantesList();
    }

    private List<Estudiante> getEstudiantesList() {
        List<Estudiante> estudiantes = new ArrayList<Estudiante>();
        estudiantes.add(new Estudiante(1,"emilio", "rey","emilio.rey@globant.com"));
        estudiantes.add(new Estudiante(2,"mariano", "reyes","mariano.reyes@gmail.com"));

        return estudiantes;
    }

    @Test
    public void listEstudiantes(){
        when(estudianteService.getAllEstudiante()).thenReturn(estudiantes);
        Assert.assertEquals(estudianteRestController.getCustomers(), estudiantes);
        verify(estudianteService, atLeastOnce()).getAllEstudiante();
    }

    @Test
    public void newEstudiante(){
        Estudiante estudianteNuevo = new Estudiante(0,"moises","garcia","moises.garcia@harriague.com");
        Estudiante estudianteEsperado = new Estudiante(1,"moises","garcia","moises.garcia@harriague.com");
        when(estudianteService.saveEstudiante(estudianteNuevo)).thenReturn(estudianteEsperado);
        Assert.assertEquals(estudianteRestController.createEstudiante(estudianteNuevo).getBody(), estudianteEsperado);
        verify(estudianteService,atLeastOnce()).saveEstudiante(estudianteNuevo);
    }

    @Test
    public void updateEstudiante(){
        Estudiante estudianteParaActualizar = new Estudiante(1,"moises","garcia","moises.garcia@harriague.com");
        Estudiante estudianteEsperado = new Estudiante(1,"moisesActualizado","garciaUpdated","moises.garcia@harriague.com");
        when(estudianteService.updateEstudiante(estudianteParaActualizar)).thenReturn(estudianteEsperado);
        Assert.assertEquals(estudianteRestController.updateEstudiante(estudianteParaActualizar).getBody(), estudianteEsperado);
        verify(estudianteService,atLeastOnce()).updateEstudiante(estudianteParaActualizar);
    }

    @Test
    public void deleteEstudiante(){
        Estudiante estudianteParaBorrar= new Estudiante(1,"moises","garcia","moises.garcia@harriague.com");
        estudianteRestController.deleteEstudiante(estudianteParaBorrar.getId());
        verify(estudianteService,atLeastOnce()).deleteEstudiante(estudianteParaBorrar.getId());
    }

}
