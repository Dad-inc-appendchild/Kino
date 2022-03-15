package com.dia.backend.data.repository;

import com.dia.backend.domain.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, String> {
}
