package com.dia.backend.data.repository;

import com.dia.backend.domain.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
}
