package com.admission.middleware.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Admitere {
    @JsonProperty("pondere_bac")
    private Double pondere_bac;

    @JsonProperty("pondere_examen")
    private Double pondere_examen;

    @JsonProperty("nota_min_admis")
    private int nota_min_admis;

    @JsonProperty("nr_locuri_buget")
    private int nr_locuri_buget;

    @JsonProperty("nr_locuri_taxa")
    private int nr_locuri_taxa;


    public Double getPondere_bac() {
        return pondere_bac;
    }

    public void setPondere_bac(Double pondere_bac) {
        this.pondere_bac = pondere_bac;
    }

    public Double getPondere_examen() {
        return pondere_examen;
    }

    public void setPondere_examen(Double pondere_examen) {
        this.pondere_examen = pondere_examen;
    }

    public int getNota_min_admis() {
        return nota_min_admis;
    }

    public void setNota_min_admis(int nota_min_admis) {
        this.nota_min_admis = nota_min_admis;
    }

    public int getNr_locuri_buget() {
        return nr_locuri_buget;
    }

    public void setNr_locuri_buget(int nr_locuri_buget) {
        this.nr_locuri_buget = nr_locuri_buget;
    }

    public int getNr_locuri_taxa() {
        return nr_locuri_taxa;
    }

    public void setNr_locuri_taxa(int nr_locuri_taxa) {
        this.nr_locuri_taxa = nr_locuri_taxa;
    }

    @Override
    public String toString() {
        return "Admitere{" +
                "pondere_bac=" + pondere_bac +
                ", pondere_examen=" + pondere_examen +
                ", nota_min_admis=" + nota_min_admis +
                ", nr_locuri_buget=" + nr_locuri_buget +
                ", nr_locuri_taxa=" + nr_locuri_taxa +
                '}';
    }
}
