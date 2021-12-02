package com.company;

import com.company.models.Ticket;

public class TicketService {

    private Repository<Ticket, Integer> ticketRepository;


    // The TicketRepository dependency will be injected to the service.
    // Because the service shouldn't have to be concerned with how the Repository is created.
    public TicketService(Repository<Ticket, Integer> ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Integer saveTicket(Ticket t) {
        return ticketRepository.save(t);
    }

    public Ticket getTicket(int id) {
        return ticketRepository.getById(id);
    }
}
