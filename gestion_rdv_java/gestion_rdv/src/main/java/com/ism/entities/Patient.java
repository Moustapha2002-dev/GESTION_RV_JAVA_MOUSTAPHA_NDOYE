package com.ism.entities;

public class Patient extends Personne {

    String antecedents;

    
    @Override
    public String toString() {
        return super.toString()+" antecedents=" + antecedents + "]";
    }


    public String getAntecedents() {
        return antecedents;
    }


    public void setAntecedents(String antecedentsList) {
        this.antecedents = antecedentsList;
    }


    
    
}