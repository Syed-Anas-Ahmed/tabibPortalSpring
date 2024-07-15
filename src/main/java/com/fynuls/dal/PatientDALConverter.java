package com.fynuls.dal;

import com.fynuls.entity.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientDALConverter {
    public static PatientDAL convertToPatientDAL(Patient patient) {
        PatientDAL patientDAL = new PatientDAL();
        patientDAL.setId(patient.getId());
        patientDAL.setName(patient.getName());
        patientDAL.setGender(patient.getGender());
        patientDAL.setPassword(patient.getPassword());
        patientDAL.setCellNumber(patient.getCellNumber());
        patientDAL.setDob(patient.getDob());
        return patientDAL;
    }

    public static List<PatientDAL> convertToPatientDALList(List<Patient> patients) {
        List<PatientDAL> patientDALS = new ArrayList<>();
        if (patients != null) {
            for (Patient patient : patients) {
                patientDALS.add(convertToPatientDAL(patient));
            }
        }
        return patientDALS;
    }
}
