package com.ism.services;

import com.ism.entities.Medecin;
import com.ism.entities.Patient;
import com.ism.repositories.MedecinImpl;
import com.ism.repositories.PatientImpl;

public class PersonneServiceImpl<T> implements PersonneService<T> {
    protected MedecinImpl medecinImpl;
    protected PatientImpl patientImpl;

    public PersonneServiceImpl(MedecinImpl medecinImpl) {
        this.medecinImpl = medecinImpl;
    }
    public PersonneServiceImpl(PatientImpl patientImpl) {
        this.patientImpl = patientImpl;
    }

    @Override
    public int add(T data) {
        if (data instanceof Medecin) {
            return medecinImpl.insert((Medecin) data);
        } else if (data instanceof Patient) {
            return patientImpl.insert((Patient) data);
        } else {
            throw new IllegalArgumentException("Type non pris en charge : " + data.getClass());
        }
    }
}
