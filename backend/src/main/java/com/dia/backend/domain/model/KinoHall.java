package com.dia.backend.domain.model;

import javax.persistence.*;

@Entity
public class KinoHall {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="kinoHallId")
  private int kinoHallId;
  private int rowX; //Can be named rowm because its a reserved keyword
  private int seat;

  public int getRowX() {
    return rowX;
  }

  public void setRowX(int row) {
    this.rowX = row;
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
