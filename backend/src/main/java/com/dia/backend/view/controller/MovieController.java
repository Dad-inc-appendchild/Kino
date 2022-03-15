package com.dia.backend.view.controller;

import com.dia.backend.data.repository.MovieRepository;
import com.dia.backend.domain.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class MovieController {

    //Test
    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/movies")
    public List<Movie> displayMovie(){
        return movieRepository.findAll();
    }

    @GetMapping("/movies/{id}")
    public Movie findMovieById(@PathVariable String id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            return movie.get();
        } else {
            return null;
        }

    }
}
