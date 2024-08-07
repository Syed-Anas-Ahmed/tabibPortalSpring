package com.fynuls.dal;

import javax.persistence.Column;

public class TreatmentDAL {

    private int id;
    private String name;
    private String detail;

    public TreatmentDAL(int id, String name, String detail) {
        this.id = id;
        this.name = name;
        this.detail = detail;
    }

    public TreatmentDAL() {
    }

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
}
