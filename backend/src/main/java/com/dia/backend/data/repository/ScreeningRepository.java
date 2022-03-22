package com.dia.backend.data.repository;

import com.dia.backend.domain.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening, Integer> {
  // build query by JPA. The method name defines the search term. No query annotation needed.
  List<Screening> findScreeningByStartTimeBetween(LocalDateTime dateTime, LocalDateTime dateTime2);
}
