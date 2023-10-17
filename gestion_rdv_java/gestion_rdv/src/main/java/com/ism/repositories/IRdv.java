package com.ism.repositories;

import java.util.ArrayList;
import com.ism.entities.Rdv;

public interface IRdv<T extends Rdv> {
    public int insert(T data);

    ArrayList<T> findAll() throws ClassNotFoundException;

    ArrayList<T> findByMedecinAndDate(String nomComplet, String Date) throws ClassNotFoundException;

    public int cancel(int id) throws ClassNotFoundException;

    public ArrayList<Rdv> findRdvByDate(String date) throws ClassNotFoundException;

    public int updateStatut(Rdv rdv) throws ClassNotFoundException;
}
