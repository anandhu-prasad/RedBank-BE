package com.javainuse.requests;

public class ContactUs_ReqBody {

    private String subject;
    private String message;

    public ContactUs_ReqBody(String subject, String message) {
        this.subject = subject;
        this.message = message;
    }

    public ContactUs_ReqBody() {
        super();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ContactUs_ReqBody{" + "subject='" + subject + '\'' + ", message='" + message + '\'' + '}';
    }
}
