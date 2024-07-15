package com.fynuls.utils;

public class ApiResponse<T> {
    private String status;
    private String type;
    private T data;

    public ApiResponse(String status, T data, String type) {
        this.status = status;
        this.data = data;
        this.type = type;
    }

    public ApiResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}