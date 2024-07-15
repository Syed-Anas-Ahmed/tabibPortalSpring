package com.fynuls.entity;

import javax.persistence.*;

@Entity
@Table(name="T_FEEDBACK")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;
    @Column(name="RATING")
    private int rating;
    @Column(name="TOKEN")
    private String token;
    @Column(name="DETAIL")
    private String detail;
    @ManyToOne
    @JoinColumn(name = "DOCTOR_ID")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;
    @Column(name="UPDATE_DATE")
    private String updateDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
