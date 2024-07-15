package com.fynuls.dal;

public class ClinicAppointments {
    /*
    SELECT p.id, p.name, p.gender, a.age, p.cellNumber, " +
                        " d.id,d.name,d.gender,d.address," +
                        " a.id,a.charges,a.followupDate,a.visitDate, a.diagnosis, a.prescription, a.weight " +
     */
    int patientId;
    String patientName;
    String patientGender;
    String patientAge;
    String patientCellNumber;
    int doctorId;
    String doctorName;
    String doctorGender;
    String doctorAddress;
    int appointmentId;
    int appointmentCharges;
    String followupDate;
    String visitDate;
    String diagnosis;
    String prescription;
    int weight;

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientCellNumber() {
        return patientCellNumber;
    }

    public void setPatientCellNumber(String patientCellNumber) {
        this.patientCellNumber = patientCellNumber;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorGender() {
        return doctorGender;
    }

    public void setDoctorGender(String doctorGender) {
        this.doctorGender = doctorGender;
    }

    public String getDoctorAddress() {
        return doctorAddress;
    }

    public void setDoctorAddress(String doctorAddress) {
        this.doctorAddress = doctorAddress;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getAppointmentCharges() {
        return appointmentCharges;
    }

    public void setAppointmentCharges(int appointmentCharges) {
        this.appointmentCharges = appointmentCharges;
    }

    public String getFollowupDate() {
        return followupDate;
    }

    public void setFollowupDate(String followupDate) {
        this.followupDate = followupDate;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
