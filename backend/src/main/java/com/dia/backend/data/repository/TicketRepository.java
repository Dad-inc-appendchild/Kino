package com.dia.backend.data.repository;

import com.dia.backend.domain.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
  @Query(value = "SELECT * FROM ticket t where t.screening_id = ?1", nativeQuery = true)
  List<Ticket> ticketsByScreeningID(int id);
}
