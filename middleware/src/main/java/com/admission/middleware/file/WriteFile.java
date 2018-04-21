package com.admission.middleware.file;


import com.admission.middleware.model.ClassifyStudent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteFile {

    private static final String FILENAME = "allStudents.txt";

    public void writeFile(List<ClassifyStudent> classifyStudents) {

        BufferedWriter bw = null;
        FileWriter fw = null;

        try {


            fw = new FileWriter(FILENAME);
            bw = new BufferedWriter(fw);
            for (ClassifyStudent classifyStudent : classifyStudents) {
                bw.write(classifyStudent.toString());
            }
            System.out.println("Done");

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }

    }

}