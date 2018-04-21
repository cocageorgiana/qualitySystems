package com.admission.middleware.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.logging.log4j.util.PropertySource;

public class Student implements Comparable<Student>{

    @JsonProperty("id")
    Integer id;

    @JsonProperty("first_name")
    String first_name;

    @JsonProperty("last_name")
    String last_name;

    @JsonProperty("medie_bac")
    Double medie_bac;

    @JsonProperty("nota_examen")
    Double nota_examen;

    @JsonProperty("medie")
    Double medie;

    public Student() {

    }
    public Student(Student student) {
        this.id = student.getId();
        this.first_name = student.getFirst_name();
        this.last_name = student.getLast_name();
        this.medie_bac = student.getMedie_bac();
        this.nota_examen = student.getNota_examen();
        this.medie = student.getMedie();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Double getMedie() {
        return medie;
    }

    public void setMedie(Double medie) {
        this.medie = medie;
    }

    public Double getMedie_bac() {
        return medie_bac;
    }

    public void setMedie_bac(Double medie_bac) {
        this.medie_bac = medie_bac;
    }

    public Double getNota_examen() {
        return nota_examen;
    }

    public void setNota_examen(Double nota_examen) {
        this.nota_examen = nota_examen;
    }

    @Override
    public int compareTo(Student student) {
        return Double.compare(medie, student.medie);
    }

}