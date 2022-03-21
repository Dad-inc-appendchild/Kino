package com.dia.backend.view.controller;

import com.dia.backend.data.repository.SeatRepository;
import com.dia.backend.domain.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/seats") // prefix for endpoints
public class SeatController {

  @Autowired SeatRepository seatRepository;

  @GetMapping("")
  public List<Seat> displaySeat() {
    return seatRepository.findAll();
  }

  @GetMapping("/{id}")
  public Seat findSeatById(@PathVariable int id) {
    Optional<Seat> seat = seatRepository.findById(id);
    if (seat.isPresent()) {
      return seat.get();
    } else {
      return null;
    }
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public void postSeat(@RequestBody Seat seat) {
    seatRepository.save(seat);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Seat> updateSeat(@PathVariable int id, @RequestBody Seat seat) {
    Optional<Seat> seat1 = seatRepository.findById(id);
    if (seat1.isPresent()) {
      seatRepository.save(seat);
      return new ResponseEntity<>(seat, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteSeat(@PathVariable int id) {
    try {
      seatRepository.deleteById(id);
      return new ResponseEntity<>("Deleted: " + id, HttpStatus.OK);
    } catch (Exception err) {
      return new ResponseEntity<>("Error deleting: " + id, HttpStatus.NOT_FOUND);
    }
  }
}
