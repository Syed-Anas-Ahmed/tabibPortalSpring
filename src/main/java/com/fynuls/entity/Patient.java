package com.fynuls.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Syed Muhammad Hassan
 * 16th December,2021
 */

@Entity
@Table(name="T_PATIENT")
public class Patient implements Cloneable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;
    @Column(name="NAME")
    private String name;
    @Column(name="GENDER")
    private String gender;
    @Column(name="PASSWORD")
    private String password;
    @Column(name="CELL_NUMBER")
    private String cellNumber;
    @Column(name="DOB")
    private String dob;
    @Column(name="status")
    private int status;

    @ManyToMany(mappedBy = "patients")
    private Set<Doctor> doctors = new HashSet<>();

    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
    private Set<AppointmentModel> appointments;

    @Column(name="UPDATE_DATE")
    private String updateDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Set<AppointmentModel> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<AppointmentModel> appointments) {
        this.appointments = appointments;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
