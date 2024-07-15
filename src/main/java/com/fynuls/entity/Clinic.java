package com.fynuls.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @author Syed Muhammad Hassan
 * 16th May,2022
 */

@Entity
@Table(name="T_CLINIC")
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;
    @Column(name="NAME")
    private String name;
    @Column(name="ADDRESS")
    private String address;
    @Column(name="LATLONG")
    private String LatLong;
    @Column(name="UPDATE_DATE")
    private String updateDate;

    @ManyToMany(mappedBy = "clinics")
    private List<Doctor> doctors;

    @OneToMany(mappedBy = "clinic", fetch = FetchType.EAGER)
    private Set<DoctorClinic> doctorClinics;

    @OneToMany(mappedBy = "clinic", fetch = FetchType.EAGER)
    private List<AppointmentModel> appointments;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatLong() {
        return LatLong;
    }

    public void setLatLong(String latLong) {
        LatLong = latLong;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Set<DoctorClinic> getDoctorClinics() {
        return doctorClinics;
    }

    public void setDoctorClinics(Set<DoctorClinic> doctorClinics) {
        this.doctorClinics = doctorClinics;
    }

    public List<AppointmentModel> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentModel> appointments) {
        this.appointments = appointments;
    }
}
