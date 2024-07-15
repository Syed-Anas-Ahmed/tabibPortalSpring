package com.fynuls.entity;

import javax.persistence.*;

@Entity
@Table(name="T_DOCTOR_SPECIALIZATION")
public class DoctorSpecialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;
    @ManyToOne
    @JoinColumn(name = "DOCTOR_ID")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "SPECIALIZATION_ID")
    private Specialization specialization;
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

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
