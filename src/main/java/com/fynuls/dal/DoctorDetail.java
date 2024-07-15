package com.fynuls.dal;

import com.fynuls.entity.*;

import java.util.List;
import java.util.Set;

public class DoctorDetail {
    private Set<Doctor> doctors;
    private Set<Clinic> clinics;
    private Set<DoctorClinic> doctorClinics;
    private Set<DoctorQualification> doctorQualifications;
    private Set<Qualification> qualifications;

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Set<Clinic> getClinics() {
        return clinics;
    }

    public void setClinics(Set<Clinic> clinics) {
        this.clinics = clinics;
    }

    public Set<DoctorClinic> getDoctorClinics() {
        return doctorClinics;
    }

    public void setDoctorClinics(Set<DoctorClinic> doctorClinics) {
        this.doctorClinics = doctorClinics;
    }

    public Set<DoctorQualification> getDoctorQualifications() {
        return doctorQualifications;
    }

    public void setDoctorQualifications(Set<DoctorQualification> doctorQualifications) {
        this.doctorQualifications = doctorQualifications;
    }

    public Set<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(Set<Qualification> qualifications) {
        this.qualifications = qualifications;
    }
}
