package com.dia.backend.domain.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Ticket {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ticketId")
  private int ticketId;

  private UUID bookingId;

  @ManyToOne
  @JoinColumn(name = "seat_id")
  private Seat seat;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  public Ticket(Seat seat) {
    this.seat = seat;
  }

  public Ticket() {
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Seat getSeat() {
    return seat;
  }

  public void setSeat(Seat seat) {
    this.seat = seat;
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
