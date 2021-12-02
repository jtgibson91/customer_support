package com.company.models;

public class TechSupportTicket extends Ticket{
    public TechSupportTicket() {
        this.ticket_type = 1;
    }
    @Override
    public void closeTicket() {
        System.out.println("Closing tech support ticket");
        this.isClosed = true;
    }
}
