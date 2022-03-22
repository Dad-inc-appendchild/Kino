package com.dia.backend.data.repository;

import com.dia.backend.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "SELECT * FROM customer c WHERE c.phone_number = ?1", nativeQuery = true)
    Optional<Customer> findByPhoneNumber(String phoneNumber);
}
