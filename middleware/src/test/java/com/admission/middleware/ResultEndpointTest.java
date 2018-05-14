package com.admission.middleware;

import com.admission.middleware.controller.DataInputController;
import com.admission.middleware.controller.PublishResultsController;
import com.admission.middleware.model.Student;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResultEndpointTest {
    private MockMvc mockMvc;

    @Mock
    DataInputController dataInputController;

    @InjectMocks
    private PublishResultsController publishResultsController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(publishResultsController)
                .build();
    }

    @Test
    public void classifyStudentTest() throws Exception{
        when(dataInputController.getAllStudents()).thenReturn(createStudents());
        when(dataInputController.getNoBudget()).thenReturn(1);
        when(dataInputController.getTaxNo()).thenReturn(1);

        String expected = "[{\"classification\":\"budget\",\"id\":2,\"first_name\":\"Ion\",\"last_name\":\"Andrei\",\"medie_bac\":10.0,\"nota_examen\":10.0,\"medie\":10.0},{\"classification\":\"fee\",\"id\":1,\"first_name\":\"Ion\",\"last_name\":\"Bogdan\",\"medie_bac\":7.0,\"nota_examen\":7.0,\"medie\":7.0}]";

        this.mockMvc.perform(get("/results")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString().contains(expected);
    }

    private Student[] createStudents() {
        Student bogdan = new Student();
        bogdan.setId(1);
        bogdan.setLast_name("Bogdan");
        bogdan.setFirst_name("Ion");
        bogdan.setMedie_bac(7.0);
        bogdan.setNota_examen(7.0);
        bogdan.setMedie(7.0);

        Student andrei = new Student();
        andrei.setId(2);
        andrei.setLast_name("Andrei");
        andrei.setFirst_name("Ion");
        andrei.setMedie_bac(10.0);
        andrei.setNota_examen(10.0);
        andrei.setMedie(10.0);

        Student[] students = new Student[2];
        students[0] = bogdan;
        students[1] = andrei;
        return students;
    }
}
