package com.fynuls.dal;

public class ClinicDAL {
    private int id;
    private String name;
    private String address;
    private String LatLong;

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
}
