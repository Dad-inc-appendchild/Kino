package com.dia.backend.domain.model;

import javax.persistence.*;

@Entity
public class Seat {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int seatId;

  public int getSeatId() {
    return seatId;
  }

  public void setSeatId(int seatId) {
    this.seatId = seatId;
  }
}
