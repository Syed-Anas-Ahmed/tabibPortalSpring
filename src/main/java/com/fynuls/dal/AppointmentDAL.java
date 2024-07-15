package com.fynuls.dal;


import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 9th June,2022
 */
public class AppointmentDAL {
    private int id;
    private String patientName;
    private String clinicName;
    private String doctorName;
    private String visitDate;
    private int tokenNumber;
    private int status;
    private int clinicTotalAppointments;
    private int clinicLastAppointmentToken;
    private int charges;
    private String prescription;
    private String diagnosis;
    private int age;
    private int weight;
    private String bloodPressure;
    private String followupDate;
    private int patientId;
    private int clinicId;
    private int doctorId;

    private List<TreatmentDAL> treatments;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<TreatmentDAL> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<TreatmentDAL> treatments) {
        this.treatments = treatments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public int getTokenNumber() {
        return tokenNumber;
    }

    public void setTokenNumber(int tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    public int getClinicTotalAppointments() {
        return clinicTotalAppointments;
    }

    public void setClinicTotalAppointments(int clinicTotalAppointments) {
        this.clinicTotalAppointments = clinicTotalAppointments;
    }

    public int getClinicLastAppointmentToken() {
        return clinicLastAppointmentToken;
    }

    public void setClinicLastAppointmentToken(int clinicLastAppointmentToken) {
        this.clinicLastAppointmentToken = clinicLastAppointmentToken;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCharges() {
        return charges;
    }

    public void setCharges(int charges) {
        this.charges = charges;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getFollowupDate() {
        return followupDate;
    }

    public void setFollowupDate(String followupDate) {
        this.followupDate = followupDate;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getClinicId() {
        return clinicId;
    }

    public void setClinicId(int clinicId) {
        this.clinicId = clinicId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
}
