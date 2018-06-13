package net.viralpatel.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.AppConfigTest;
import net.viralpatel.spring.model.Estudiante;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by emilio on 6/3/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class,
        classes = { AppConfigTest.class})
@TestPropertySource(value = { "classpath:test_application.properties" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ActiveProfiles("test")
public class EstudianteRestControllerTest extends AbstractJUnit4SpringContextTests{
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void givenEstudiantesURI_withGet_whenMockMVC_thenVerifyResponse() throws Exception {
         this.mockMvc.perform(get("/estudiantes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "[{\"id\":1,\"firstName\":\"emilio\",\"lastName\":\"rey\",\"email\":\"emilio.rey@globant.com\"}," +
                                "{\"id\":2,\"firstName\":\"luciana\",\"lastName\":\"bazan\",\"email\":\"luciana.bazan@globant.com\"}]"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();
    }

    @Test
    public void givenEstudiantesURI_withPost_whenMockMVC_thenResponseOK() throws Exception {
        this.mockMvc.perform(post("/estudiantes")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"firstName\":\"Micaela\",\"lastName\":\"Rey\",\"email\":\"micaela.rey@gmail.com\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.firstName").value("Micaela"))
                .andExpect(jsonPath("$.lastName").value("Rey"))
                .andExpect(jsonPath("$.email").value("micaela.rey@gmail.com"));
    }

    @Test
    public void givenEstudiantesURI_withGet_PathvariableURI_whenMockMVC_thenVerifyResponse() throws Exception {

        this.mockMvc.perform(get("/estudiantes/{id}", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("emilio"))
                .andExpect(jsonPath("$.lastName").value("rey"))
                .andExpect(jsonPath("$.email").value("emilio.rey@globant.com"))
                .andReturn();
    }

    @Test
    public void addEstudiante_to_then_deleteURI_withGet_Pathvariable_whenMockMVC_thenVerifyResponse() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("************ Listado inicial de estudiantes*********");
        String estudiantes = this.mockMvc.perform(get("/estudiantes")).andReturn().getResponse().getContentAsString();
        System.out.println(estudiantes);
        List<Estudiante> lista = objectMapper.readValue(estudiantes, List.class);
        int comparador = lista.size();
        System.out.println(comparador);
        System.out.println("******************************************************************************************* \n");

        System.out.println("************ Guardado de un nuevo estudiante *********");
        String resultGuardado = this.mockMvc.perform(post("/estudiantes")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"firstName\":\"Eugenia\",\"lastName\":\"Perez\",\"email\":\"eugenia.perez@gmail.com\"}"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(resultGuardado);
        System.out.println("******************************************************************************************* \n");

        System.out.println("************ Lista de todos los estudiantes en la base de datos despues de guardar********* ");
        estudiantes = this.mockMvc.perform(get("/estudiantes")).andReturn().getResponse().getContentAsString();
        System.out.println(estudiantes);
        lista = objectMapper.readValue(estudiantes, List.class);
        Assert.assertTrue(lista.size() == comparador+1);
        System.out.println("******************************************************************************************* \n");

        Estudiante estudianteGuardado = objectMapper.readValue(resultGuardado,Estudiante.class);

        MvcResult mvcResult = this.mockMvc.perform(delete("/estudiantes/{id}", estudianteGuardado.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        System.out.println("\n\n************ Lista de todos los estudiantes en la base de datos despues de borrar *********");
        estudiantes = this.mockMvc.perform(get("/estudiantes")).andReturn().getResponse().getContentAsString();
        System.out.println(estudiantes);
        lista = objectMapper.readValue(estudiantes, List.class);
        Assert.assertTrue(lista.size() == comparador);
        System.out.println("******************************************************************************************* \n");
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesGreetController() {
        ServletContext servletContext = wac.getServletContext();
        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("estudianteRestController"));

    }
}
