package com.admission.middleware.controller;

import com.admission.middleware.file.WriteExcel;
import com.admission.middleware.file.WriteFile;
import com.admission.middleware.model.ClassifyStudent;
import com.admission.middleware.model.Student;
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

    @RequestMapping(path = "results", method = RequestMethod.GET, produces = "application/json")
    public List<ClassifyStudent> classifyStudent() {
        List<ClassifyStudent> classifyStudents = new ArrayList<>();
        DataInputController dataInputController = new DataInputController();
        Student[] students = dataInputController.getAllStudents();
        WriteFile file = new WriteFile();
        WriteExcel excel = new WriteExcel();

        Integer buget = dataInputController.getNrBudget();//apelezi
        Integer fee = dataInputController.getTaxNo();//apelezi;

        //sortati toti studentii dupa medie
        Arrays.sort(students, Collections.reverseOrder());
        for (int i = 0; i < buget; i++) {
            ClassifyStudent classifyStudent = new ClassifyStudent(students[i]);
            classifyStudent.setClassification("budget");
            classifyStudents.add(classifyStudent);
        }

        for (int i = buget; i < buget + fee; i++) {
            ClassifyStudent classifyStudent = new ClassifyStudent(students[i]);
            classifyStudent.setClassification("fee");
            classifyStudents.add(classifyStudent);
        }

        for (int i = buget + fee; i < Array.getLength(students); i++) {
            ClassifyStudent classifyStudent = new ClassifyStudent(students[i]);
            classifyStudent.setClassification("respins");
            classifyStudents.add(classifyStudent);

        }
        excel.storeIntoExcel(classifyStudents);
        file.writeFile(classifyStudents);
        return classifyStudents;
    }

}
