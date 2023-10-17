package com.ism.entities;

public class Medecin extends Personne {

    private String specialite;

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    @Override
    public String toString() {
        return super.toString()+" specialite=" + specialite + "]";
    }
    
}