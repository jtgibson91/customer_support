package com.company;

import com.company.models.ProductSupportTicket;
import com.company.models.TechSupportTicket;
import com.company.models.Ticket;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    private Scanner scanner;
    private TicketService ticketService;
    private Repository<Ticket, Integer> ticketRepository;

    public static void main(String[] args) {
	    Main app = new Main();
	    app.run();
    }
    public Main() {
        this.scanner = new Scanner(System.in);
        this.ticketRepository = new TicketRepository();
        this.ticketService = new TicketService(this.ticketRepository);
    }
    public void run() {
        ((TicketRepository)this.ticketRepository).init(); // make sure the file repository has been properly initialized
        Ticket t = null;

        System.out.println("What kind of support ticket would you like to submit");
        System.out.println("1. Tech Support");
        System.out.println("2. Product Support");

        int ticketTypeSelection = this.scanner.nextInt(); // reading the user input, should be 1 or 2
        this.scanner.nextLine();
        t = createTicketFromSelection(ticketTypeSelection); // create a new ticket from the user input;

        // reach here, start collecting information on the ticket

        // submitter
        System.out.println("Please give user your email address");
            String submitterEmail = this.scanner.nextLine();
            t.setSubmitter(submitterEmail);


        // description
        System.out.println("Please describe your issue");
            String description = this.scanner.nextLine();
            t.setDescription(description);


        // set the submission date
        t.setSubmittedOn(LocalDateTime.now());

        this.ticketService.saveTicket(t);

        System.out.println("Ticket successfully created");
    }

    private Ticket createTicketFromSelection(int selectionType) {
        return selectionType == 1 ? new TechSupportTicket() : new ProductSupportTicket();
    }
}
