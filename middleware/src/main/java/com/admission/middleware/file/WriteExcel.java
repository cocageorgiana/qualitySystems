package com.admission.middleware.file;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import com.admission.middleware.model.ClassifyStudent;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WriteExcel {

    private static final String FILE_NAME = "StudentsExcel.xlsx";

    public void storeIntoExcel(List<ClassifyStudent> classifyStudentList) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Student List");
        Object[][] datatypes = new Object[classifyStudentList.size() + 1][7];

        datatypes[0][0] = "Id";
        datatypes[0][1] = "First Name";
        datatypes[0][2] = "Last Name";
        datatypes[0][3] = "Medie Bac";
        datatypes[0][4] = "Nota Examen";
        datatypes[0][5] = "Medie";
        datatypes[0][6] = "Clasificare";

        int position = 1;
        for (ClassifyStudent iterator : classifyStudentList) {
            datatypes[position][0] = iterator.getId();
            datatypes[position][1] = iterator.getFirst_name();
            datatypes[position][2] = iterator.getLast_name();
            datatypes[position][3] = iterator.getMedie_bac().toString();
            datatypes[position][4] = iterator.getNota_examen().toString();
            datatypes[position][5] = iterator.getMedie().toString();
            datatypes[position][6] = iterator.getClassification();


            position++;
        }


        int rowNum = 0;
        System.out.println("Creating excel");

        for (Object[] datatype : datatypes) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : datatype) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }
}
