package com.javainuse.requests;

public class EmailRequestBody {

    private String email;

    public EmailRequestBody() {
        super();
    }

    public EmailRequestBody(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
