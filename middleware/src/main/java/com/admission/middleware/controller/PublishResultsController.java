package com.admission.middleware.controller;

import com.admission.middleware.file.WriteExcel;
import com.admission.middleware.file.WriteFile;
import com.admission.middleware.model.ClassifyStudent;
import com.admission.middleware.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class PublishResultsController {
    @Autowired
    DataInputController dataInputController;

    @RequestMapping(path = "results", method = RequestMethod.GET, produces = "application/json")
    public List<ClassifyStudent> classifyStudent() {
        WriteFile file = new WriteFile();
        WriteExcel excel = new WriteExcel();

        List<ClassifyStudent> classifyStudents = new ArrayList<>();

        Student[] students = dataInputController.getAllStudents();

        //Integer buget = dataInputController.getNoBudget();//apelezi
        //Integer fee = dataInputController.getTaxNo();//apelezi;

        Integer buget = 270;
        Integer fee = 90;

        Arrays.sort(students, Collections.reverseOrder());


        int iterator = 0;

        while (iterator < Array.getLength(students)) {

            ClassifyStudent classifyStudent = new ClassifyStudent(students[iterator]);

            if (buget > 0) {
                classifyStudent.setClassification("budget");
                buget--;
            } else if (fee > 0) {
                classifyStudent.setClassification("fee");
            } else {
                classifyStudent.setClassification("respins");
            }
            classifyStudents.add(classifyStudent);
            iterator++;
        }

        file.writeFile(classifyStudents);
        excel.storeIntoExcel(classifyStudents);
        return classifyStudents;
    }

}