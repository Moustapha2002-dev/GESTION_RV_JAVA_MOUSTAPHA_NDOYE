package com.ism.entities;

import java.util.ArrayList;

public abstract class Personne {
    protected static int nbrePersonne;

    public static int getNbrePersonne() {
        return nbrePersonne;
    }

    public static void setNbrePersonne(int nbrePersonne) {
        Personne.nbrePersonne = nbrePersonne;
    }

    protected int id;
    protected String nomComplet;
    protected int type;

    public Personne(int id, String nomComplet, int type, ArrayList<Rdv> rdvs) {
        this.id = id;
        this.nomComplet = nomComplet;
        this.type = type;
        Rdvs = rdvs;
    }

    public int getType() {
        return type;
    }

    public void setType(int i) {
        this.type = i;
    }

    ArrayList<Rdv> Rdvs = new ArrayList<>();

    public ArrayList<Rdv> getRdvs() {
        return Rdvs;
    }

    public Personne(int id, String nomComplet) {
        id = ++nbrePersonne;
        this.nomComplet = nomComplet;
    }

    public Personne() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = ++nbrePersonne;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
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
        Personne other = (Personne) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Personne [id=" + id + ", nomComplet=" + nomComplet + ", type=" + type + ", Rdvs=" + Rdvs + "]";
    }

}
