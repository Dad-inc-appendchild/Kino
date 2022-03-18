package com.dia.backend.view.controller;

import com.dia.backend.data.repository.TicketRepository;
import com.dia.backend.domain.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets") // prefix for endpoints
public class TicketController {

  @Autowired TicketRepository ticketRepository;

  @GetMapping("")
  public List<Ticket> displayTicket() {
    return ticketRepository.findAll();
  }

  @GetMapping("/{id}")
  public Ticket findTicketById(@PathVariable int id) {
    Optional<Ticket> ticket = ticketRepository.findById(id);
    if (ticket.isPresent()) {
      return ticket.get();
    } else {
      return null;
    }
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public void postTicket(@RequestBody Ticket ticket) {
    ticketRepository.save(ticket);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Ticket> updateTicket(@PathVariable int id, @RequestBody Ticket ticket) {
    Optional<Ticket> ticket1 = ticketRepository.findById(id);
    if (ticket1.isPresent()) {
      ticketRepository.save(ticket);
      return new ResponseEntity<>(ticket, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteTicket(@PathVariable int id) {
    try {
      ticketRepository.deleteById(id);
      return new ResponseEntity<>("Deleted: " + id, HttpStatus.OK);
    } catch (Exception err) {
      return new ResponseEntity<>("Error deleting: " + id, HttpStatus.NOT_FOUND);
    }
  }
}
