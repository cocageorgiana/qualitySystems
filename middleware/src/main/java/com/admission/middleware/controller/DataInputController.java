package com.admission.middleware.controller;

import com.admission.middleware.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class DataInputController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(path = "student/{id}", method = RequestMethod.GET, produces = "application/json")
    public Student getStudent(@PathVariable("id") Integer id) {
        Student student;

        String url = "" + id;

        student = (Student) restTemplate.getForObject(url, Student.class);
        return student;
    }

    @RequestMapping(path = "student", method = RequestMethod.GET, produces = "application/json")
    public Student[] getAllStudents() {
        Student[] student;

        String url = "" ;

        student =  restTemplate.getForObject(url, Student[].class);
        return student;
    }

    @RequestMapping(path = "student", method = RequestMethod.POST, produces = "application/json")
    public Student createStudent(@RequestParam(name = "first_name") String first_name,
                                 @RequestParam(name = "last_name") String last_name,
                                 @RequestParam(name = "medie_bac") Double medie_bac,
                                 @RequestParam(name = "nota_examen") Double nota_examen,
                                 @RequestParam(name = "medie") Double medie) {
        Student student = new Student();
        student.setFirst_name(first_name);
        student.setLast_name(last_name);
        student.setMedie(medie);
        student.setNota_examen(nota_examen);
        student.setMedie_bac(medie_bac);
        String url = "";

        student = (Student) restTemplate.postForObject(url, student, Student.class);
        return student;
    }

    @RequestMapping(path = "student/{id}", method = RequestMethod.PUT, produces = "application/json")
    public Student updateStudent(@PathVariable(name = "id") Integer id,
                                 @RequestParam(name = "first_name") String first_name,
                                 @RequestParam(name = "last_name") String last_name,
                                 @RequestParam(name = "medie_bac") Double medie_bac,
                                 @RequestParam(name = "nota_examen") Double nota_examen,
                                 @RequestParam(name = "medie") Double medie) {
        Student student = new Student();
        student.setFirst_name(first_name);
        student.setLast_name(last_name);
        student.setMedie(medie);
        student.setNota_examen(nota_examen);
        student.setMedie_bac(medie_bac);
        String url = "" + id;

        student = (Student) restTemplate.postForObject(url, student, Student.class);
        return student;
    }

    @RequestMapping(path = "student/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public Student deleteStudent(@PathVariable("id") Integer id) {
        Student student;

        String url = "" + id;

        student = (Student) restTemplate.getForObject(url, Student.class);
        return student;
    }

    @RequestMapping(path = "budget", method = RequestMethod.GET, produces = "application/json")
    public Integer getNrBudget() {
        Integer nrBudget;

        String url = "";

        nrBudget = (Integer) restTemplate.getForObject(url, Integer.class);
        return nrBudget;
    }

    @RequestMapping(path = "tax", method = RequestMethod.GET, produces = "application/json")
    public Integer getTaxNo() {
        Integer nrTaxes;

        String url = "";

        nrTaxes = (Integer) restTemplate.getForObject(url, Integer.class);
        return nrTaxes;
    }
}
