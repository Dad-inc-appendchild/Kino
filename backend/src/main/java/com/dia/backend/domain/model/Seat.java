package com.dia.backend.domain.model;

import javax.persistence.*;

@Entity
public class Seat {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int seatId;

  private int seatRow;

  private int seatNumber;

  public Seat() {}

  public Seat(int row, int number) {
    setSeatRow(row);
    setSeatNumber(number);
  }

  public int getSeatRow() {
    return seatRow;
  }

  public void setSeatRow(int seatRow) {
    this.seatRow = seatRow;
  }

  public int getSeatNumber() {
    return seatNumber;
  }

  public void setSeatNumber(int seatNumber) {
    this.seatNumber = seatNumber;
  }

  public int getSeatId() {
    return seatId;
  }

  public void setSeatId(int seatId) {
    this.seatId = seatId;
  }
}
