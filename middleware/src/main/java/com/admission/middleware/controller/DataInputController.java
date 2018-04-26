package com.admission.middleware.controller;

import com.admission.middleware.model.Admitere;
import com.admission.middleware.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
@CrossOrigin
@RestController
public class DataInputController {

    @Autowired
    RestTemplate restTemplate;

    String url = "http://admissioncontest.us-east-2.elasticbeanstalk.com/AdmissionContestServlet";

    @RequestMapping(path = "students/{id}", method = RequestMethod.GET, produces = "application/json")//merge
    public Student getStudent(@PathVariable("id") Integer id) {

        String param = "{\n" +
                "\t\"operation\": \"select\",\n" +
                "\t\"from\": \"students\",\n" +
                "\t\"cond1\": {\n" +
                "\t\t\"key\": \"id\",\n" +
                "\t\t\"operator\": \"=\",\n" +
                "\t\t\"value\": \"" + id + "\"\n" +
                "\t\t}\n" +
                "}";

        Student[] student;
        student = (Student[]) restTemplate.postForObject(url, param, Student[].class);
        return student[0];
    }

    @RequestMapping(path = "students", method = RequestMethod.POST)//merge
    public Student[] getAllStudents() {
        String param = " {\n" +
                "          \t\"operation\": \"select\",\n" +
                "          \t\"from\": \"students\"\n" +
                "          }";
        Student[] student;
        student = (Student[]) restTemplate.postForObject(url, param, Student[].class);
        return student;
    }

    @RequestMapping(path = "createStudent", method = RequestMethod.POST, produces = "application/json") //merge
    public ResponseEntity<Void> createStudent(@RequestParam(name = "first_name") String first_name,
                                              @RequestParam(name = "last_name") String last_name,
                                              @RequestParam(name = "medie_bac") Double medie_bac,
                                              @RequestParam(name = "nota_examen") Double nota_examen) {

        String request = "{\n" +
                "\t\"operation\": \"insert\",\n" +
                "\t\"into\": \"students\",\n" +
                "\t\"values\": {\n" +
                "\t\t\"first_name\": \"" + first_name + "\",\n" +
                "\t\t\"last_name\": \"" + last_name + "\",\n" +
                "\t\t\"medie_bac\": " + medie_bac + ",\n" +
                "\t\t\"nota_examen\": " + nota_examen + "\n" +
                "\t}\n" +
                "}";

        String response;
        response = (String) restTemplate.postForObject(url, request, String.class);

        if (response.contains("1") && response.contains("-1") == false) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "students/{id}", method = RequestMethod.PUT, produces = "application/json") //merge
    public ResponseEntity<Void> updateStudent(@PathVariable(name = "id") Integer id,
                                              @RequestParam(name = "first_name") String first_name,
                                              @RequestParam(name = "last_name") String last_name,
                                              @RequestParam(name = "medie_bac") Double medie_bac,
                                              @RequestParam(name = "nota_examen") Double nota_examen) {
        String request = "{\n" +
                "\t\"operation\": \"update\",\n" +
                "\t\"into\": \"students\",\n" +
                "\t\"values\": {\n" +
                "\t\t\"first_name\": \"" + first_name + "\",\n" +
                "\t\t\"last_name\": \"" + last_name + "\",\n" +
                "\t\t\"medie_bac\": " + medie_bac + ",\n" +
                "\t\t\"nota_examen\": " + nota_examen + "\n" +
                "\t}\n,\"" +
                "cond1\": {\n" +
                "\t\t\"key\": \"id\",\n" +
                "\t\t\"operator\": \"=\",\n" +
                "\t\t\"value\": " + id + "\n" +
                "\t\t}" +
                "}";

        String response;
        response = (String) restTemplate.postForObject(url, request, String.class);

        if (response.contains("1") && response.contains("-1") == false) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "students/{id}", method = RequestMethod.DELETE, produces = "application/json") //merge
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Integer id) {
        String param = "{\n" +
                "\t\"operation\": \"delete\",\n" +
                "\t\"from\": \"students\",\n" +
                "\t\"cond1\": {\n" +
                "\t\t\"key\": \"id\",\n" +
                "\t\t\"operator\": \"=\",\n" +
                "\t\t\"value\": \"" + id + "\"\n" +
                "\t\t}\n" +
                "}";

        String response;
        response = (String) restTemplate.postForObject(url, param, String.class);

        if (response.contains("1") && response.contains("-1") == false) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "budget", method = RequestMethod.GET)
    public Integer getNoBudget() {
        String request = "{\n" +
                " \"operation\": \"select\",\n" +
                " \"from\": \"criterii_insert_students\"\n" +
                "}";

        RestTemplate restTemplate = new RestTemplate();

        Admitere admitere = restTemplate.postForObject(url, request, Admitere.class);

        return admitere.getNr_locuri_buget();
    }

    @RequestMapping(path = "tax", method = RequestMethod.GET)
    public Integer getTaxNo() {
        String request = "{\n" +
                " \"operation\": \"select\",\n" +
                " \"from\": \"criterii_insert_students\"\n" +
                "}";

        RestTemplate restTemplate = new RestTemplate();

        Admitere admitere = restTemplate.postForObject(url, request, Admitere.class);

        return admitere.getNr_locuri_buget();
    }
}
