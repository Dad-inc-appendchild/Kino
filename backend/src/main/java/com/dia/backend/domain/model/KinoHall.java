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
