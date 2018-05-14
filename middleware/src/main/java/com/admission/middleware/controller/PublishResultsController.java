package com.admission.middleware.controller;

import com.admission.middleware.model.ClassifyStudent;
import com.admission.middleware.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
public class PublishResultsController {
    @Autowired
    DataInputController dataInputController;

    @RequestMapping(path = "results", method = RequestMethod.GET, produces = "application/json")
    public List<ClassifyStudent> classifyStudent() {

        List<ClassifyStudent> classifyStudents = new ArrayList<>();

        Student[] students = dataInputController.getAllStudents();

        Integer buget = dataInputController.getNoBudget();//apelezi
        Integer fee = dataInputController.getTaxNo();//apelezi;

        Arrays.sort(students, Collections.reverseOrder());

        int iterator = 0;

        while (iterator < Array.getLength(students)) {

            ClassifyStudent classifyStudent = new ClassifyStudent(students[iterator]);

            if (buget > 0) {
                classifyStudent.setClassification("budget");
                buget--;
            } else if (fee > 0) {
                classifyStudent.setClassification("fee");
                fee--;
            } else {
                classifyStudent.setClassification("respins");
            }
            classifyStudents.add(classifyStudent);
            iterator++;
        }

        return classifyStudents;
    }

}
