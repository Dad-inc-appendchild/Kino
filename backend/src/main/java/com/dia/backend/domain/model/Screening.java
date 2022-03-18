package com.dia.backend.domain.model;

import javax.persistence.*;

@Entity
public class Screening {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int screeningId;

  @ManyToOne
  @JoinColumn(name = "kinoHallId")
  private KinoHall kinoHall;

  @ManyToOne
  @JoinColumn(name = "movieId")
  private Movie movie;

  public Movie getMovie() {
    return movie;
  }

  public KinoHall getKinoHall() {
    return kinoHall;
  }

  public void setKinoHall(KinoHall kinoHall) {
    this.kinoHall = kinoHall;
  }

  public void setMovie(Movie movie) {
    this.movie = movie;
  }

  public int getScreeningId() {
    return screeningId;
  }

  public void setScreeningId(int screeningId) {
    this.screeningId = screeningId;
  }
}
