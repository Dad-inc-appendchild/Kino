package com.dia.backend.data.repository;

import com.dia.backend.domain.model.Screening;
import com.dia.backend.domain.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
}
