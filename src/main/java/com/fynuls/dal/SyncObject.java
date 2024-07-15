package com.fynuls.dal;

import com.fynuls.entity.*;

import java.util.List;

public class SyncObject {
    List<DoctorDAL> doctors;
    List<ClinicDAL> clinics;
    List<AppointmentDAL> appointments;
    List<PatientDAL> patients;
    List<Treatment> treatments;
    List<TreatmentBank> treatmentBanks;
    Feedback feedback;
    String token;
    Dashboard dashboard;

    List<LineGraph> lineGraphs;

    List<BarChart> barCharts;

    List<ClinicAppointments> clinicAppointments;

    public List<DoctorDAL> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<DoctorDAL> doctors) {
        this.doctors = doctors;
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
    }

    public List<TreatmentBank> getTreatmentBanks() {
        return treatmentBanks;
    }

    public void setTreatmentBanks(List<TreatmentBank> treatmentBanks) {
        this.treatmentBanks = treatmentBanks;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public void setAppointments(List<AppointmentDAL> appointments) {
        this.appointments = appointments;
    }

    public List<PatientDAL> getPatients() {
        return patients;
    }

    public void setPatients(List<PatientDAL> patients) {
        this.patients = patients;
    }

    public List<ClinicDAL> getClinics() {
        return clinics;
    }

    public void setClinics(List<ClinicDAL> clinics) {
        this.clinics = clinics;
    }

    public List<LineGraph> getLineGraphs() {
        return lineGraphs;
    }

    public void setLineGraphs(List<LineGraph> lineGraphs) {
        this.lineGraphs = lineGraphs;
    }

    public List<AppointmentDAL> getAppointments() {
        return appointments;
    }

    public List<BarChart> getBarCharts() {
        return barCharts;
    }

    public void setBarCharts(List<BarChart> barCharts) {
        this.barCharts = barCharts;
    }

    public List<ClinicAppointments> getClinicAppointments() {
        return clinicAppointments;
    }

    public void setClinicAppointments(List<ClinicAppointments> clinicAppointments) {
        this.clinicAppointments = clinicAppointments;
    }
}
