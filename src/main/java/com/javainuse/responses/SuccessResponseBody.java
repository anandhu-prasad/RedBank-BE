package com.javainuse.responses;

public class SuccessResponseBody {
    private boolean success;

    public SuccessResponseBody(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
