package com.dia.backend.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Ticket {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="ticketId")
  private int ticketId;
  private UUID bookingId;
  private int kinoId;
  //private int customerId; Giver fejl, når vi kompiler med denne attribut.

  @OneToMany
  @JoinColumn(name="ticketId")
  @JsonBackReference
  private Set<Customer> tickets = new HashSet<>();

  public Set<Customer> getTickets() {
    return tickets;
  }

  public void setTickets(Set<Customer> tickets) {
    this.tickets = tickets;
  }
/*
  @ManyToOne
  @JoinColumn(name = "customerId")
  private Customer customer;

   */

  public int getKinoId() {
    return kinoId;
  }

  public void setKinoId(int kinoId) {
    this.kinoId = kinoId;
  }

  public UUID getBookingId() {
    return bookingId;
  }

  public void setBookingId(UUID bookingId) {
    this.bookingId = bookingId;
  }


  public int getTicketId() {
    return ticketId;
  }

  public void setTicketId(int ticketId) {
    this.ticketId = ticketId;
  }
}
