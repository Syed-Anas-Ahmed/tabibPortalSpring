package com.fynuls.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Syed Muhammad Hassan
 * 13th June, 2022
 */

@Entity
@Table(name="T_LOGINSTATUS")
public class LoginStatus {

    @Id
    @Column(name="ID")
    private int id;
    @Column(name="USERNAME")
    private String username;
    @Column(name="TYPE")
    private String type;
    @Column(name="TOKEN")
    private String token;
    @Column(name="FCMTOKEN")
    private String fcmToken;
    @Column(name="STATUS")
    private String status;
    @Column(name="UPDATE_DATE")
    private Date update_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }
}
