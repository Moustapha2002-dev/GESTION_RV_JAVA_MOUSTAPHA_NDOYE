package com.ism.services;

import java.util.ArrayList;

import com.ism.repositories.RdvImpl;
import com.ism.entities.Rdv;

public class RdvServiceImpl<T> implements RdvService<Rdv> {

    protected RdvImpl rdvImpl;

    public RdvServiceImpl(RdvImpl rdvImpl) {
        this.rdvImpl = rdvImpl;
    }

    @Override
    public int add(Rdv data) {
        return rdvImpl.insert(data);
    }

    @Override
    public ArrayList<Rdv> showMdc(String nom, String date) throws ClassNotFoundException {
        return rdvImpl.findByMedecinAndDate(nom, date);
    }

    @Override
    public int cancel(int id) throws ClassNotFoundException {
        return rdvImpl.cancel(id);
    }

    @Override
    public ArrayList<Rdv> show() throws ClassNotFoundException {
        return rdvImpl.findAll();
    }
}
