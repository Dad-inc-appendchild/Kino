package com.dia.backend.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
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

  private LocalDateTime startTime;

  private LocalDateTime endTime;

  @Getter @Setter private String event;

  public Screening() {}

  public Screening(Movie movie, KinoHall kinoHall) {
    setMovie(movie);
    setKinoHall(kinoHall);
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }

  public LocalDateTime getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
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

  public void setMovie(Movie movie) {
    this.movie = movie;
  }

  public KinoHall getKinoHall() {
    return kinoHall;
  }

  public void setKinoHall(KinoHall kinoHall) {
    this.kinoHall = kinoHall;
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
