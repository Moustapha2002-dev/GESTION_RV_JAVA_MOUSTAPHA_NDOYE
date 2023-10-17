package com.ism.repositories;

import com.ism.entities.Personne;

public interface Repository<T extends Personne> {
    public int insert(T data);
    
}