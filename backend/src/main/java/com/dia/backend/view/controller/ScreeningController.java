package com.dia.backend.view.controller;

import com.dia.backend.data.repository.ScreeningRepository;
import com.dia.backend.data.repository.TicketRepository;
import com.dia.backend.domain.model.Screening;
import com.dia.backend.domain.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/screenings") // prefix for endpoints
public class ScreeningController {

  @Autowired ScreeningRepository screeningRepository;
  @Autowired TicketRepository ticketRepository;

  @GetMapping("")
  public List<Screening> displayScreening() {
    return screeningRepository.findAll();
  }

  @GetMapping("/{id}")
  public Screening findScreeningById(@PathVariable int id) {
    Optional<Screening> screening = screeningRepository.findById(id);
    if (screening.isPresent()) {
      return screening.get();
    } else {
      return null;
    }
  }

  @GetMapping("/date/{date}")
  public List<Screening> findScreeningsByDate(@PathVariable String date) {
    return screeningRepository.findScreeningByStartTimeBetween(
        LocalDateTime.parse(date + "T00:00"), LocalDateTime.parse(date + "T23:59"));
  }

  @GetMapping("/month/{month}")
  public List<Screening> findScreeningsByMonth(@PathVariable String month) {
    LocalDateTime localDateTime = LocalDateTime.parse(month + "T00:00");
    LocalDateTime startTime = localDateTime.with(TemporalAdjusters.firstDayOfMonth());
    LocalDateTime endTime =
        localDateTime.with(TemporalAdjusters.lastDayOfMonth()).plusHours(23).plusMinutes(59);
    return screeningRepository.findScreeningByStartTimeBetween(startTime, endTime);
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public void postScreening(@RequestBody Screening screening) {
    screeningRepository.save(screening);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Screening> updateScreening(
      @PathVariable int id, @RequestBody Screening screening) {
    Optional<Screening> screening1 = screeningRepository.findById(id);
    if (screening1.isPresent()) {
      screeningRepository.save(screening);
      return new ResponseEntity<>(screening, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteScreening(@PathVariable int id) {
    try {
      screeningRepository.deleteById(id);
      return new ResponseEntity<>("Deleted: " + id, HttpStatus.OK);
    } catch (Exception err) {
      return new ResponseEntity<>("Error deleting: " + id, HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/{id}/tickets")
  public List<Ticket> seatsByScreening(@PathVariable int id) {
    return ticketRepository.ticketsByScreeningID(id);
  }
}
