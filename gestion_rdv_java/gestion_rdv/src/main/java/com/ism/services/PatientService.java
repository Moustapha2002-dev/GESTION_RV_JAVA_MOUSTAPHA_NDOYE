package com.ism.services;

import com.ism.entities.Patient;
import com.ism.repositories.PatientImpl;

public class PatientService extends PersonneServiceImpl<Patient> {

    public PatientService(PatientImpl patientImpl) {
        super(patientImpl);
        //TODO Auto-generated constructor stub
    }

}
