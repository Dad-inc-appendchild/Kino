package com.dia.backend.domain.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

  @OneToMany
  @JoinColumn(name = "screening_id")
  private List<Ticket> tickets;

  public Screening() {}

  public Screening(Movie movie, KinoHall kinoHall) {
    setMovie(movie);
    setKinoHall(kinoHall);
  }

  public List<Ticket> getTickets() {
    return tickets;
  }

  public void setTickets(List<Ticket> tickets) {
    this.tickets = tickets;
  }

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

  public List<Ticket> generateTickets() {
    ArrayList<Ticket> tickets = new ArrayList<>();
    for (Seat seat : kinoHall.getSeats()) {
      tickets.add(new Ticket(seat));
    }
    this.tickets = tickets;
    return tickets;
  }
}
