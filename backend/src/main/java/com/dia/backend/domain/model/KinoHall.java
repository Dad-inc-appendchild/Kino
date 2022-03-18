package com.dia.backend.domain.model;

import javax.persistence.*;

@Entity
public class KinoHall {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="kinoHallId")
  private int kinoHallId;
  private int seatRow; //Cant be named row because its a reserved keyword
  private int seat;

  @OneToOne
  @JoinColumn(name = "ticketId")
  private Ticket ticket;

  public Ticket getTicket() {
    return ticket;
  }

  public void setTicket(Ticket ticket) {
    this.ticket = ticket;
  }

  public int getSeatRow() {
    return seatRow;
  }

  public void setSeatRow(int row) {
    this.seatRow = row;
  }

  public int getSeat() {
    return seat;
  }

  public void setSeat(int seat) {
    this.seat = seat;
  }

  public int getKinoHallId() {
    return kinoHallId;
  }

  public void setKinoHallId(int kinoHallId) {
    this.kinoHallId = kinoHallId;
  }
}
