package com.javainuse.requests;

public class MyDriveCancel_ReqBody {
    private Boolean status;

    public MyDriveCancel_ReqBody() {
        super();
    }

    public MyDriveCancel_ReqBody(Boolean status) {
        this.status = status;
    }
    public Boolean getstatus() {
        return status;
    }

    public void setstatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MyDriveCancel_ReqBody{" +
                "status=" + status +
                '}';
    }
}
