package com.javainuse.requests;

public class InviteRequestBody {
    private String eventId;
    private String eventType;
    private int acceptance;
    private String rejectionMessage;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(int acceptance) {
        this.acceptance = acceptance;
    }

    public String getRejectionMessage() {
        return rejectionMessage;
    }

    public void setRejectionMessage(String rejectionMessage) {
        this.rejectionMessage = rejectionMessage;
    }

    @Override
    public String toString() {
        return "InviteResponse{" +
                "eventId='" + eventId + '\'' +
                ", eventType='" + eventType + '\'' +
                ", acceptance=" + acceptance +
                ", RejectionMessage='" + rejectionMessage + '\'' +
                '}';
    }
}
