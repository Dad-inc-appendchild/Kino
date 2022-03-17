package com.dia.backend.data.mapper;

import com.dia.backend.data.repository.*;
import com.dia.backend.domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataMapper implements CommandLineRunner {

  @Autowired MovieRepository movieRepository;

  @Autowired CustomerRepository customerRepository;

  @Autowired ScreeningRepository screeningRepository;

  @Autowired SeatRepository seatRepository;

  @Autowired KinoHallRepository kinoHallRepository;

  @Override
  public void run(String... args) throws Exception {

    Movie movie = new Movie();
    movie.setTitle("test");
    movie.setYear(2020);
    movie.setCountry("Test country");
    movie.setLanguage("Test Language");
    movieRepository.save(movie);

    Customer customer = new Customer();
    customer.setName("John");
    customer.setPhoneNumber("0011223344");
    customerRepository.save(customer);

    screeningRepository.save(new Screening());

    seatRepository.save(new Seat());

    kinoHallRepository.save(new KinoHall());
  }
}
