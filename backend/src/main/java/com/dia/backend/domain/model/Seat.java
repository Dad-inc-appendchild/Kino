package com.dia.backend.domain.model;

import javax.persistence.*;

@Entity
public class Seat {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int seatId;

  @ManyToOne
  @JoinColumn(name = "screeningId")
  private Screening screening;

  public Screening getScreening() {
    return screening;
  }

  public void setScreening(Screening screening) {
    this.screening = screening;
  }

  public int getSeatId() {
    return seatId;
  }

  public void setSeatId(int seatId) {
    this.seatId = seatId;
  }
}
