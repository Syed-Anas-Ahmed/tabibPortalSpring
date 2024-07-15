package com.fynuls.dal;

public class DoctorClinicDAL {
    private int id;
    private ClinicDAL clinic;
    private int charges;
    private String startTime;
    private String endTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClinicDAL getClinic() {
        return clinic;
    }

    public void setClinic(ClinicDAL clinic) {
        this.clinic = clinic;
    }

    public int getCharges() {
        return charges;
    }

    public void setCharges(int charges) {
        this.charges = charges;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
