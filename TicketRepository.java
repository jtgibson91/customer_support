package com.company;

import com.company.models.ProductSupportTicket;
import com.company.models.TechSupportTicket;
import com.company.models.Ticket;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketRepository implements Repository<Ticket, Integer>{
    private final static String FILENAME = "resources/tickets";
    private List<Ticket> tickets = new ArrayList<>(); // in-mem cache
    private AtomicInteger idGen; // our id generator for saving new tickets


    // read the file and load up all tickets,
    // then add them an in-mem cache
    public void init() {
        try {
            FileReader reader = new FileReader(FILENAME);
            Scanner scanner = new Scanner(reader);
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");
                int tokensLen = tokens.length;
                Ticket t = null;

                if(tokens[tokensLen - 1].equals("1")) {
                    t = new TechSupportTicket();
                } else {
                    t = new ProductSupportTicket();
                }

                // the fields are broken down in this order
                //   0           1                   2                      3                     4                  5
                // newId+","+t.getSubmitter()+","+t.getDescription()+","+t.getSubmittedOn()+","+t.isClosed()+","+Ticket.TICKET_TYPE
                t.setId(Integer.parseInt(tokens[0]));
                t.setSubmitter(tokens[1]);
                t.setDescription(tokens[2]);
                t.setSubmittedOn(LocalDateTime.parse(tokens[3]));
                t.setClosed(Boolean.parseBoolean(tokens[4]));

                tickets.add(t);
            }
            idGen = new AtomicInteger(tickets.size() + 1);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Could not initialize TicketService", e);
        }
    }


    @Override
    public Integer save(Ticket t) {
        int newId = idGen.getAndIncrement();
        // serialize the Ticket object to a string for writing to a file
        String ticketSerialized = newId+","+t.getSubmitter()+","+t.getDescription()+","+t.getSubmittedOn()+","+t.isClosed()+","+t.getTicket_type();
        FileWriter writer = null;

        try {
            writer = new FileWriter(FILENAME, true);
            writer.write(ticketSerialized + "\n"); //write to buffer
            writer.flush(); // flush the buffer
            tickets.add(t); // add the ticket to the in-mem cache

        } catch (IOException e) {
            throw new RuntimeException("Could not save ticket", e);
        }
        return newId;
    }

    @Override
    public Ticket getById(Integer integer) {
        return tickets.get(integer);
    }
}
