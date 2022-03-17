package com.dia.backend.view.controller;

import com.dia.backend.data.repository.ScreeningRepository;
import com.dia.backend.domain.model.Screening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/screenings") // prefix for endpoints
public class ScreeningController {

  @Autowired ScreeningRepository screeningRepository;

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
}
