package com.dia.backend.domain.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Ticket {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="ticketId")
  private int ticketId;
  private UUID bookingId;
  private int kinoId;
  private int customerId;

  @ManyToOne
  @JoinColumn(name = "customerId")
  private Customer customer;

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

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public int getTicketId() {
    return ticketId;
  }

  public void setTicketId(int ticketId) {
    this.ticketId = ticketId;
  }
}
