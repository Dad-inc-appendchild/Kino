package com.dia.backend.view.controller;

import com.dia.backend.data.repository.MovieRepository;
import com.dia.backend.domain.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies") // prefix for endpoints
public class MovieController {

  @Autowired MovieRepository movieRepository;

  @GetMapping("")
  public List<Movie> displayMovie() {
    return movieRepository.findAll();
  }

  @GetMapping("/{id}")
  public Movie findMovieById(@PathVariable Long id) {
    Optional<Movie> movie = movieRepository.findById(id);
    if (movie.isPresent()) {
      return movie.get();
    } else {
      return null;
    }
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public void postMovie(@RequestBody Movie movie) {
    movieRepository.save(movie);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
    Optional<Movie> movie1 = movieRepository.findById(id);
    if (movie1.isPresent()) {
      movieRepository.save(movie);
      return new ResponseEntity<>(movie, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
    try {
      movieRepository.deleteById(id);
      return new ResponseEntity<>("Deleted: " + id, HttpStatus.OK);
    } catch (Exception err) {
      return new ResponseEntity<>("Error deleting: " + id, HttpStatus.NOT_FOUND);
    }
  }
}
