package com.dia.backend.data.mapper;

import com.dia.backend.data.repository.CustomerRepository;
import com.dia.backend.data.repository.MovieRepository;
import com.dia.backend.domain.model.Customer;
import com.dia.backend.domain.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataMapper implements CommandLineRunner {

  @Autowired
  MovieRepository movieRepository;

  @Autowired
  CustomerRepository customerRepository;

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
  }
}
