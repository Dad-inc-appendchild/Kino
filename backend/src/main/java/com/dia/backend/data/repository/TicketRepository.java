package com.dia.backend.data.repository;

import com.dia.backend.domain.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    /*
    @Query("select kino join ticket on kino.ticket.ticket_id=kino.customer.customer_id")
    List<Ticket> findTicketsBy();
     */
}
