package com.fynuls.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Syed Muhammad Hassan
 * 13th May,2022
 */


@Entity
@Table(name="T_DOCTOR")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "AGE")
    private int age;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "PRIORITY")
    private int priority;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "TYPE")
    private int type;

    @Column(name = "UPDATE_DATE")
    private String updateDate;

    @ManyToMany
    @JoinTable(
            name = "DOCTOR_PATIENT",
            joinColumns = @JoinColumn(name = "DOCTOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "PATIENT_ID")
    )
    private Set<Patient> patients = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "DOCTOR_CLINIC",
            joinColumns = @JoinColumn(name = "DOCTOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "CLINIC_ID")
    )
    private List<Clinic> clinics;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
    private Set<DoctorClinic> doctorClinics;

    @ManyToMany
    @JoinTable(
            name = "DOCTOR_QUALIFICATION",
            joinColumns = @JoinColumn(name = "DOCTOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "QUALIFICATION_ID")
    )
    private Set<Qualification> qualifications = new HashSet<>();

    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
    private Set<DoctorQualification> doctorQualifications;

    @ManyToMany
    @JoinTable(
            name = "DOCTOR_SPECIALIZATION",
            joinColumns = @JoinColumn(name = "DOCTOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "SPECIALIZATION_ID")
    )
    private Set<Specialization> specializations = new HashSet<>();

    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
    private Set<DoctorSpecialization> doctorSpecializations;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public List<Clinic> getClinics() {
        return clinics;
    }

    public void setClinics(List<Clinic> clinics) {
        this.clinics = clinics;
    }

    public Set<DoctorClinic> getDoctorClinics() {
        return doctorClinics;
    }

    public void setDoctorClinics(Set<DoctorClinic> doctorClinics) {
        this.doctorClinics = doctorClinics;
    }

    public Set<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(Set<Qualification> qualifications) {
        this.qualifications = qualifications;
    }

    public Set<DoctorQualification> getDoctorQualifications() {
        return doctorQualifications;
    }

    public void setDoctorQualifications(Set<DoctorQualification> doctorQualifications) {
        this.doctorQualifications = doctorQualifications;
    }

    public Set<AppointmentModel> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<AppointmentModel> appointments) {
        this.appointments = appointments;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(Set<Specialization> specializations) {
        this.specializations = specializations;
    }

    public Set<DoctorSpecialization> getDoctorSpecializations() {
        return doctorSpecializations;
    }

    public void setDoctorSpecializations(Set<DoctorSpecialization> doctorSpecializations) {
        this.doctorSpecializations = doctorSpecializations;
    }
}
