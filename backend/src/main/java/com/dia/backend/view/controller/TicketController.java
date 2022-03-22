package com.dia.backend.view.controller;

import com.dia.backend.data.repository.CustomerRepository;
import com.dia.backend.data.repository.TicketRepository;
import com.dia.backend.domain.model.Customer;
import com.dia.backend.domain.model.CustomerAndTIckersWrappper;
import com.dia.backend.domain.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/tickets") // prefix for endpoints
public class TicketController {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    TicketRepository ticketRepository;

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

    @PostMapping("/book")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void postTicket(@RequestBody CustomerAndTIckersWrappper customerAndTickets) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerAndTickets.getCustomer().getCustomerId());
        if (optionalCustomer.isPresent()) {
            Customer presentCustomer = optionalCustomer.get();
            System.out.println(presentCustomer.getCustomerId());
            for (Ticket ticket : customerAndTickets.getTickets()) {
                Optional<Ticket> optionalTicket = ticketRepository.findById(ticket.getTicketId());
                if (optionalTicket.isPresent()) {
                    Ticket presentTicket = optionalTicket.get();
                    System.out.println(presentTicket.getTicketId());
                    presentTicket.setCustomer(presentCustomer);
                    ticketRepository.save(presentTicket);
                }
            }
        }
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
