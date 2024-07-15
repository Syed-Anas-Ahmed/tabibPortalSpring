package com.fynuls.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Syed Muhammad Hassan
 * 16th May,2022
 */

@Entity
@Table(name="T_QUALIFICATION")
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;
    @Column(name="NAME")
    private String name;

    @ManyToMany(mappedBy = "qualifications")
    private Set<Doctor> doctors = new HashSet<>();

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

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }
}
