package com.dia.backend.domain.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Ticket {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private int ticketId;

  @Column(unique = true)
  private UUID bookingId;
  private int kinoId;
  private int customerId;

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
