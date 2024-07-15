package com.fynuls.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 14th May,2022
 */

@Entity
@Table(name="T_APPOINTMENT")
public class AppointmentModel {
    @Id
    @GeneratedValue
    @Column(name="ID")
    private int id;
    @ManyToOne
    @JoinColumn(name = "DOCTOR_ID")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "CLINIC_ID")
    private Clinic clinic;
    @Column(name="VISIT_DATE")
    private String visitDate;
    @Column(name="TOKEN")
    private int token;
    @Column(name="DIAGNOSIS")
    private String diagnosis;
    @Column(name="PRESCRIPTION")
    private String prescription;
    @Column(name="followupDate")
    private String followupDate;
    @Column(name="bloodPressure")
    private String bloodPressure;
    @Column(name="weight")
    private int weight;
    @Column(name="charges")
    private int charges;
    @Column(name="status")
    private int status;
    @Column(name="age")
    private int age;

    @OneToMany(mappedBy = "appointmentModel", cascade = CascadeType.ALL)
    private List<Treatment> treatments;

    @Column(name="UPDATE_DATE")
    private String updateDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getFollowupDate() {
        return followupDate;
    }

    public void setFollowupDate(String followupDate) {
        this.followupDate = followupDate;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getCharges() {
        return charges;
    }

    public void setCharges(int charges) {
        this.charges = charges;
    }


    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
    }
}
