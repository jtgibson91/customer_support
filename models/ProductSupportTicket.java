package com.company.models;

public class ProductSupportTicket extends Ticket{
    public ProductSupportTicket() {
        this.ticket_type = 2;
    }
    @Override
    public void closeTicket() {
        System.out.println("Closing product support ticket");
        this.isClosed = true;
    }
}
