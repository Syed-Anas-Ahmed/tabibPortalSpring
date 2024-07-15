package com.fynuls.entity;

import com.fynuls.entity.Clinic;

import javax.persistence.*;

/**
 * @author Syed Muhammad Hassan
 * 16th May,2022
 */

@Entity
@Table(name="T_DOCTOR_CLINIC")
public class DoctorClinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;
    @ManyToOne
    @JoinColumn(name = "DOCTOR_ID")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "CLINIC_ID")
    private Clinic clinic;
    @Column(name="CHARGES")
    private int charges;
    @Column(name="startTime")
    private String startTime;
    @Column(name="endTime")
    private String endTime;
    @Column(name="UPDATE_DATE")
    private String updateDate;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getCharges() {
        return charges;
    }

    public void setCharges(int charges) {
        this.charges = charges;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }


}
