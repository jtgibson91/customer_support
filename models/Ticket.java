package com.company.models;

import java.time.LocalDateTime;

public abstract class Ticket {
    protected int ticket_type;
    protected int id;
    protected String submitter;
    protected String description;
    protected LocalDateTime submittedOn;
    protected boolean isClosed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getSubmittedOn() {
        return submittedOn;
    }

    public void setSubmittedOn(LocalDateTime submittedOn) {
        this.submittedOn = submittedOn;
    }

    public boolean isClosed() {
        return this.isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public int getTicket_type() {
        return ticket_type;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "submitter='" + submitter + '\'' +
                ", description='" + description + '\'' +
                ", submittedOn=" + submittedOn +
                ", isClosed=" + isClosed +
                '}';
    }

    public abstract void closeTicket();
}
