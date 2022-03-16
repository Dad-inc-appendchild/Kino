package com.dia.backend.view.controller;

import com.dia.backend.data.repository.KinoHallRepository;
import com.dia.backend.domain.model.KinoHall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kinoHalls") // prefix for endpoints
public class KinoHallController {

  @Autowired
  KinoHallRepository kinoHallRepository;

  @GetMapping("")
  public List<KinoHall> displayKinoHall() {
    return kinoHallRepository.findAll();
  }

  @GetMapping("/{id}")
  public KinoHall findKinoHallById(@PathVariable int id) {
    Optional<KinoHall> kinoHall = kinoHallRepository.findById(id);
    if (kinoHall.isPresent()) {
      return kinoHall.get();
    } else {
      return null;
    }
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public void postKinoHall(@RequestBody KinoHall kinoHall) {
    kinoHallRepository.save(kinoHall);
  }

  @PutMapping("/{id}")
  public ResponseEntity<KinoHall> updateKinoHall(@PathVariable int id, @RequestBody KinoHall kinoHall) {
    Optional<KinoHall> kinoHall1 = kinoHallRepository.findById(id);
    if (kinoHall1.isPresent()) {
      kinoHallRepository.save(kinoHall);
      return new ResponseEntity<>(kinoHall, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteKinoHall(@PathVariable int id) {
    try {
      kinoHallRepository.deleteById(id);
      return new ResponseEntity<>("Deleted: " + id, HttpStatus.OK);
    } catch (Exception err) {
      return new ResponseEntity<>("Error deleting: " + id, HttpStatus.NOT_FOUND);
    }
  }
}
