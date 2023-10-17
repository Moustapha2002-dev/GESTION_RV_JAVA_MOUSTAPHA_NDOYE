package com.ism.repositories;

import com.ism.entities.Personne;

public interface IMedecin<T extends Personne> extends Repository<T> {
    public String findMedecin(String nom) throws ClassNotFoundException;
    
}
