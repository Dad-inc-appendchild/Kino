package com.dia.backend.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Screening {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int screeningId;
  private int customerId;
  private int kinoId;
  private int movieId;

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public int getKinoId() {
    return kinoId;
  }

  public void setKinoId(int kinoId) {
    this.kinoId = kinoId;
  }

  public int getMovieId() {
    return movieId;
  }

  public void setMovieId(int movieId) {
    this.movieId = movieId;
  }

  public int getScreeningId() {
    return screeningId;
  }

  public void setScreeningId(int screeningId) {
    this.screeningId = screeningId;
  }
}
