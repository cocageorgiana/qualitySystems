package com.admission.middleware;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MiddlewareApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResultEndpointTest {

    @LocalServerPort
    public int port;

    @Test
    public void testRetrieveStudentCourse() throws JSONException {
        HttpHeaders headers = new HttpHeaders();

        TestRestTemplate restTemplate = new TestRestTemplate();

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("middleware/results"),
                HttpMethod.GET, entity, String.class);

        String expected = "[{\"classification\":\"budget\",\"id\":18,\"first_name\":\"Ciprian-Gabriel\",\"last_name\":\"Cusmuliuc\",\"medie_bac\":10.0,\"nota_examen\":10.0,\"medie\":10.0},{\"classification\":\"budget\",\"id\":3,\"first_name\":\"Ionel\",\"last_name\":\"Port\",\"medie_bac\":10.0,\"nota_examen\":9.0,\"medie\":9.5},{\"classification\":\"budget\",\"id\":12,\"first_name\":\"Vasile\",\"last_name\":\"Grigore\",\"medie_bac\":8.45,\"nota_examen\":9.2,\"medie\":8.825},{\"classification\":\"budget\",\"id\":13,\"first_name\":\"Vasile\",\"last_name\":\"Grigore\",\"medie_bac\":8.45,\"nota_examen\":9.2,\"medie\":8.825},{\"classification\":\"budget\",\"id\":15,\"first_name\":\"Vasile\",\"last_name\":\"Grigore\",\"medie_bac\":8.0,\"nota_examen\":9.0,\"medie\":8.5},{\"classification\":\"fee\",\"id\":17,\"first_name\":\"Vasile\",\"last_name\":\"Grigore\",\"medie_bac\":8.0,\"nota_examen\":9.0,\"medie\":8.5},{\"classification\":\"fee\",\"id\":5,\"first_name\":\"popescu\",\"last_name\":\"ionel\",\"medie_bac\":8.0,\"nota_examen\":8.0,\"medie\":8.0},{\"classification\":\"fee\",\"id\":9,\"first_name\":\"test\",\"last_name\":\"test now 2\",\"medie_bac\":6.98,\"nota_examen\":9.0,\"medie\":7.99},{\"classification\":\"fee\",\"id\":14,\"first_name\":\"Mircea\",\"last_name\":\"Mihai\",\"medie_bac\":8.0,\"nota_examen\":7.0,\"medie\":7.5},{\"classification\":\"fee\",\"id\":16,\"first_name\":\"Mircea\",\"last_name\":\"Mihai\",\"medie_bac\":8.0,\"nota_examen\":7.0,\"medie\":7.5},{\"classification\":\"respins\",\"id\":8,\"first_name\":\"test\",\"last_name\":\"test now\",\"medie_bac\":6.98,\"nota_examen\":8.0,\"medie\":7.49},{\"classification\":\"respins\",\"id\":11,\"first_name\":\"ion\",\"last_name\":\"ion\",\"medie_bac\":9.0,\"nota_examen\":5.0,\"medie\":7.0},{\"classification\":\"respins\",\"id\":10,\"first_name\":\"Dorian\",\"last_name\":\"Ion\",\"medie_bac\":5.0,\"nota_examen\":5.0,\"medie\":5.0},{\"classification\":\"respins\",\"id\":6,\"first_name\":\"test nou\",\"last_name\":\"test\",\"medie_bac\":6.98,\"nota_examen\":0.01,\"medie\":3.495}]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
