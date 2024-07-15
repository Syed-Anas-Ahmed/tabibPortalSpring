package com.fynuls.dal;

import com.fynuls.entity.*;
import com.fynuls.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DoctorDALConverter {

    public static DoctorDAL convertToDoctorDAL(Doctor doctor) {
        DoctorDAL doctorDAL = new DoctorDAL();
        doctorDAL.setId(doctor.getId());
        doctorDAL.setName(doctor.getName());
        doctorDAL.setAddress(doctor.getAddress());
        doctorDAL.setAge(doctor.getAge());
        doctorDAL.setPriority(doctor.getPriority());
        doctorDAL.setUsername(doctor.getUsername());
        doctorDAL.setType(doctor.getType());
        doctorDAL.setGender(doctor.getGender());

        // Convert List<Clinic> to List<ClinicDAL>

        List<DoctorClinicDAL> doctorClinicDALS = convertToDoctorClinicDAL(doctor.getDoctorClinics());
        doctorDAL.setDoctorClinicDALS(doctorClinicDALS);

        // Convert Set<Qualification> to Set<QualificationDAL>
        Set<QualificationDAL> qualificationDALS = convertQualificationsToQualificationDAL(doctor.getDoctorQualifications());
        doctorDAL.setQualifications(qualificationDALS);

        // Convert Set<Specialization> to Set<SpecializationDAL>
        Set<SpecializationDAL> specializationDALS = convertSpecializationToSpecializationDAL(doctor.getDoctorSpecializations());
        doctorDAL.setSpecializations(specializationDALS);

        return doctorDAL;
    }

    public static List<DoctorClinicDAL> convertToDoctorClinicDAL(Set<DoctorClinic> doctorClinics) {
        List<DoctorClinicDAL> doctorClinicDALS = new ArrayList<>();
        if (doctorClinics != null) {
            for (DoctorClinic doctorClinic : doctorClinics) {
                DoctorClinicDAL doctorClinicDAL = new DoctorClinicDAL();
                doctorClinicDAL.setId(doctorClinic.getId());
                doctorClinicDAL.setCharges(doctorClinic.getCharges());
                doctorClinicDAL.setStartTime(doctorClinic.getStartTime());
                doctorClinicDAL.setEndTime(doctorClinic.getEndTime());
                doctorClinicDAL.setClinic(convertClinicsToClinicDAL(doctorClinic.getClinic()));
                doctorClinicDALS.add(doctorClinicDAL);
            }
        }

        return doctorClinicDALS;
    }

    private static ClinicDAL convertClinicsToClinicDAL(Clinic clinic) {
        ClinicDAL clinicDAL = new ClinicDAL();
        clinicDAL.setId(clinic.getId());
        clinicDAL.setName(clinic.getName());
        return clinicDAL;
    }

    private static Set<QualificationDAL> convertQualificationsToQualificationDAL(Set<DoctorQualification> doctorQualifications) {
        Set<QualificationDAL> qualificationDALS = new HashSet<>();
        if (doctorQualifications != null && !doctorQualifications.isEmpty()) {
            for (DoctorQualification doctorQualification : doctorQualifications) {
                Qualification qualification = doctorQualification.getQualification();
                if (qualification != null) {
                    QualificationDAL qualificationDAL = new QualificationDAL();
                    qualificationDAL.setId(qualification.getId());
                    qualificationDAL.setName(qualification.getName());
                    // Copy other Qualification fields
                    qualificationDALS.add(qualificationDAL);
                }
            }
        }

        return qualificationDALS;
    }

    private static Set<SpecializationDAL> convertSpecializationToSpecializationDAL(Set<DoctorSpecialization> doctorSpecializations) {
        Set<SpecializationDAL> specializationDALS = new HashSet<>();
        if (doctorSpecializations != null && !doctorSpecializations.isEmpty()) {
            for (DoctorSpecialization doctorSpecialization : doctorSpecializations) {
                Specialization specialization = doctorSpecialization.getSpecialization();
                if (specialization != null) {
                    SpecializationDAL specializationDAL = new SpecializationDAL();
                    specializationDAL.setId(specialization.getId());
                    specializationDAL.setName(specialization.getName());
                    // Copy other Qualification fields
                    specializationDALS.add(specializationDAL);
                }
            }
        }

        return specializationDALS;
    }
}