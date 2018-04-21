package com.admission.middleware.model;

public class ClassifyStudent extends Student{

    public ClassifyStudent(Student student){
        super(student);
    }

    String classification;

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getClassification() {
        return classification;
    }

    @Override
    public String toString() {
        return "ClassifyStudent{" +
                "classification='" + classification + '\'' +
                ", id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", medie_bac=" + medie_bac +
                ", nota_examen=" + nota_examen +
                ", medie=" + medie +
                "} " + super.toString();
    }
}
