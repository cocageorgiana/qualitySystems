package com.admission.middleware;

import com.admission.middleware.controller.DataInputController;
import com.admission.middleware.model.Admitere;
import com.admission.middleware.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataInputTest {

    private MockMvc mockMvc;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private DataInputController dataInputController;

    String url = "http://admissioncontest.us-east-2.elasticbeanstalk.com/AdmissionContestServlet";

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(dataInputController)
                .build();
    }

    @Test
    public void getAllStudents() throws Exception {
        String param = " {\n" +
                "          \t\"operation\": \"select\",\n" +
                "          \t\"from\": \"students\"\n" +
                "          }";

        Student[] students = new Student[1];
        students[0] = createTestStudent();

        when(restTemplate.postForObject(url, param, Student[].class)).thenReturn(students);

        this.mockMvc.perform(get("/students")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString().contains(students[0].getFirst_name());
    }

    @Test
    public void createNewStudent() throws Exception {
        Student student = createTestStudent();

        String request = "{\n" +
                "\t\"operation\": \"insert\",\n" +
                "\t\"into\": \"students\",\n" +
                "\t\"values\": {\n" +
                "\t\t\"first_name\": \"" + student.getFirst_name() + "\",\n" +
                "\t\t\"last_name\": \"" + student.getLast_name() + "\",\n" +
                "\t\t\"medie_bac\": " + student.getMedie_bac() + ",\n" +
                "\t\t\"nota_examen\": " + student.getNota_examen() + "\n" +
                "\t}\n" +
                "}";

        when(restTemplate.postForObject(url, request, String.class)).thenReturn("{\"response\": 1}");

        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isCreated());

    }

    @Test
    public void deleteStudent() throws Exception {
        Integer id = 3;

        String param = "{\n" +
                "\t\"operation\": \"delete\",\n" +
                "\t\"from\": \"students\",\n" +
                "\t\"cond1\": {\n" +
                "\t\t\"key\": \"id\",\n" +
                "\t\t\"operator\": \"=\",\n" +
                "\t\t\"value\": \"" + id + "\"\n" +
                "\t\t}\n" +
                "}";


        when(restTemplate.postForObject(url, param, String.class)).thenReturn("{\"response\": 1}");

        this.mockMvc.perform(delete("/students/3")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    public void deleteStudentBadRequest() throws Exception {
        Integer id = 3;

        String param = "{\n" +
                "\t\"operation\": \"delete\",\n" +
                "\t\"from\": \"students\",\n" +
                "\t\"cond1\": {\n" +
                "\t\t\"key\": \"id\",\n" +
                "\t\t\"operator\": \"=\",\n" +
                "\t\t\"value\": \"" + id + "\"\n" +
                "\t\t}\n" +
                "}";


        when(restTemplate.postForObject(url, param, String.class)).thenReturn("{\"response\": -1}");

        this.mockMvc.perform(delete("/students/3")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

    }

    @Test
    public void getStudent() throws Exception {
        Integer id = 3;

        String param = "{\n" +
                "\t\"operation\": \"select\",\n" +
                "\t\"from\": \"students\",\n" +
                "\t\"cond1\": {\n" +
                "\t\t\"key\": \"id\",\n" +
                "\t\t\"operator\": \"=\",\n" +
                "\t\t\"value\": \"" + id + "\"\n" +
                "\t\t}\n" +
                "}";

        Student[] students = new Student[1];
        students[0] = createTestStudent();

        when(restTemplate.postForObject(url, param, Student[].class)).thenReturn(students);

        this.mockMvc.perform(get("/students/3")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    public void updateStudent() throws Exception {
        Student student = createTestStudent();

        Integer id = 3;

        String request = "{\n" +
                "\t\"operation\": \"update\",\n" +
                "\t\"into\": \"students\",\n" +
                "\t\"values\": {\n" +
                "\t\t\"first_name\": \"" + student.getFirst_name() + "\",\n" +
                "\t\t\"last_name\": \"" + student.getLast_name() + "\",\n" +
                "\t\t\"medie_bac\": " + student.getMedie_bac() + ",\n" +
                "\t\t\"nota_examen\": " + student.getNota_examen() + "\n" +
                "\t}\n,\"" +
                "cond1\": {\n" +
                "\t\t\"key\": \"id\",\n" +
                "\t\t\"operator\": \"=\",\n" +
                "\t\t\"value\": " + id + "\n" +
                "\t\t}" +
                "}";


        when(restTemplate.postForObject(url, request, String.class)).thenReturn("{\"response\": 1}");

        mockMvc.perform(put("/students/3")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isOk());


    }

    @Test
    public void updateStudentBadRequest() throws Exception {
        Student student = createTestStudent();

        Integer id = 3;

        String request = "{\n" +
                "\t\"operation\": \"update\",\n" +
                "\t\"into\": \"students\",\n" +
                "\t\"values\": {\n" +
                "\t\t\"first_name\": \"" + student.getFirst_name() + "\",\n" +
                "\t\t\"last_name\": \"" + student.getLast_name() + "\",\n" +
                "\t\t\"medie_bac\": " + student.getMedie_bac() + ",\n" +
                "\t\t\"nota_examen\": " + student.getNota_examen() + "\n" +
                "\t}\n,\"" +
                "cond1\": {\n" +
                "\t\t\"key\": \"id\",\n" +
                "\t\t\"operator\": \"=\",\n" +
                "\t\t\"value\": " + id + "\n" +
                "\t\t}" +
                "}";


        when(restTemplate.postForObject(url, request, String.class)).thenReturn("{\"response\": -1}");

        mockMvc.perform(put("/students/3")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isBadRequest());


    }

    @Test
    public void getNoBudget() throws Exception {
        Admitere admitere = createTestAdmitere();

        String request = "{\n" +
                " \"operation\": \"select\",\n" +
                " \"from\": \"criterii_insert_students\"\n" +
                "}";

        when(restTemplate.postForObject(url, request, Admitere.class)).thenReturn(admitere);

        mockMvc.perform(get("/budget")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void getTaxNo() throws Exception {
        Admitere admitere = createTestAdmitere();

        String request = "{\n" +
                " \"operation\": \"select\",\n" +
                " \"from\": \"criterii_insert_students\"\n" +
                "}";

        when(restTemplate.postForObject(url, request, Admitere.class)).thenReturn(admitere);

        mockMvc.perform(get("/tax")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    private Admitere createTestAdmitere() {
        Admitere admitere = new Admitere();

        admitere.setNr_locuri_buget(270);
        admitere.setNr_locuri_taxa(90);
        return admitere;
    }

    private Student createTestStudent() {
        Student student = new Student();
        student.setLast_name("Bogdan");
        student.setFirst_name("Ion");
        student.setMedie_bac(6.7);
        student.setNota_examen(4.4);
        student.setMedie(5.32);
        return student;
    }
}
