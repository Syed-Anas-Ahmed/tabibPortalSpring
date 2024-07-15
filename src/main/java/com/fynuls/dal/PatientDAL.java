package com.fynuls.dal;

import com.fynuls.entity.AppointmentModel;
import com.fynuls.entity.Doctor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

public class PatientDAL {
    private int id;
    private String name;
    private String gender;
    private String password;
    private String cellNumber;
    private String dob;
    private Set<Doctor> doctors = new HashSet<>();
    private Set<AppointmentModel> appointments;

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
}
