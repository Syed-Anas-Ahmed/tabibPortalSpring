package com.fynuls.dal;

import java.util.List;

public class Response {
    private String status;
    private String message;
    private SyncObject syncObject;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SyncObject getSyncObject() {
        return syncObject;
    }

    public void setSyncObject(SyncObject syncObject) {
        this.syncObject = syncObject;
    }
}
