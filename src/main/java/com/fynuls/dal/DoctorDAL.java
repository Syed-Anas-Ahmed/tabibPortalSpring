package com.fynuls.dal;

import java.util.List;
import java.util.Set;

public class DoctorDAL {
    private int id;
    private String name;
    private String address;
    private String gender;
    private int age;
    private int priority;
    private String username;
    private int type;
    private List<DoctorClinicDAL> doctorClinicDALS;
    private Set<QualificationDAL> qualifications;
    private Set<SpecializationDAL> specializations;
    private Set<PatientDAL> patients;


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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<DoctorClinicDAL> getDoctorClinicDALS() {
        return doctorClinicDALS;
    }

    public void setDoctorClinicDALS(List<DoctorClinicDAL> doctorClinicDALS) {
        this.doctorClinicDALS = doctorClinicDALS;
    }

    public Set<QualificationDAL> getQualifications() {
        return qualifications;
    }

    public void setQualifications(Set<QualificationDAL> qualifications) {
        this.qualifications = qualifications;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<SpecializationDAL> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(Set<SpecializationDAL> specializations) {
        this.specializations = specializations;
    }
}
