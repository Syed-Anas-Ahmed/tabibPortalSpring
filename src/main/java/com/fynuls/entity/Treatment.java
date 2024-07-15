package com.fynuls.entity;

import javax.persistence.*;

/**
 * @author Syed Muhammad Hassan
 * 17th August,2022
 */


@Entity
@Table(name="T_TREATMENT")
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;
    @Column(name="NAME")
    private String name;
    @Column(name="DETAIL")
    private String detail;
    @ManyToOne
    @JoinColumn(name = "APPOINTMENT_ID")
    private AppointmentModel appointmentModel;

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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public AppointmentModel getAppointmentModel() {
        return appointmentModel;
    }

    public void setAppointmentModel(AppointmentModel appointmentModel) {
        this.appointmentModel = appointmentModel;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
