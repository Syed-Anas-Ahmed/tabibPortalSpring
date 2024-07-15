package com.fynuls.entity;

import javax.persistence.*;

/**
 * @author Syed Muhammad Hassan
 * 16th May,2022
 */

@Entity
@Table(name="T_DOCTOR_QUALIFICATION")
public class DoctorQualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;
    @ManyToOne
    @JoinColumn(name = "DOCTOR_ID")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "QUALIFICATION_ID")
    private Qualification qualification;
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

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
