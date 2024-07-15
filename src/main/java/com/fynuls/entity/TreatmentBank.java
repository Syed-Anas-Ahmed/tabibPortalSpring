package com.fynuls.entity;


import javax.persistence.*;

/**
 * @author Syed Muhammad Hassan
 * 17th August,2022
 */

@Entity
@Table(name="T_TREATMENT_BANK")
public class TreatmentBank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;
    @Column(name="NAME")
    private String name;
    @Column(name="CHARGES")
    private int charges;
    @Column(name="DOCTORTYPE")
    private int doctorType;
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

    public int getCharges() {
        return charges;
    }

    public void setCharges(int charges) {
        this.charges = charges;
    }

    public int getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(int doctorType) {
        this.doctorType = doctorType;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
