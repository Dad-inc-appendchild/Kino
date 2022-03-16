package com.dia.backend.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class KinoHall {
  @Id
  private int kinoHallId;
  private int row;
  private int seat;

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
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
