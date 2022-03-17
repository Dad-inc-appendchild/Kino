package com.dia.backend.data.repository;

import com.dia.backend.domain.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreeningRepository extends JpaRepository<Screening, Integer> {}
