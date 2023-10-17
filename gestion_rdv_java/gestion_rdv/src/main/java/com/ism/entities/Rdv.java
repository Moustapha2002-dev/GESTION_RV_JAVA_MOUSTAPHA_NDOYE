package com.ism.entities;

public class Rdv {
    private static int nbreRdv;
    private String patient;
    public void setPatient(String patient2) {
        this.patient = patient2;
    }
    public String getPatient() {
        return patient;
    }
    private String medecin;
    public void setMedecin(String medecin2) {
        this.medecin = medecin2;
    }
    public String getMedecin() {
        return medecin;
    }
    public static int getNbreRdv() {
        return nbreRdv;
    }
    public static void setNbreRdv(int nbreRdv) {
        Rdv.nbreRdv = nbreRdv;
    }
    
    private int id;
    private String date;
    private int statut;
    public Rdv(String patient, String medecin, String date, int statut) {
        this.patient = patient;
        this.medecin = medecin;
        this.date = date;
        this.statut = statut;
    }
    public Rdv(int id, String date, int statut) {
        id =++ nbreRdv;
        this.date = date;
        this.statut = statut;
    }
    public Rdv(int i, String string, String string2, String string3, String string4) {
    }
    public Rdv() {
    }
    public Rdv(int int1, String string, int int2, String string2, String string3) {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        id =++ nbreRdv;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getStatut() {
        return statut;
    }
    public void setStatut(int statut) {
        this.statut = statut;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Rdv other = (Rdv) obj;
        if (id != other.id)
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Rdv [id=" + id + ", date=" + date + ", statut=" + statut + "]";
    }
}