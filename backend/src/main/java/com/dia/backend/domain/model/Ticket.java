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

  @OneToOne
  @JoinColumn(name = "seatId")
  private Seat seat;

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
