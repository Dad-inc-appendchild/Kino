package com.dia.backend.data.mapper;

import com.dia.backend.data.repository.MovieRepository;
import com.dia.backend.data.repository.ProductRepository;
import com.dia.backend.domain.model.Movie;
import com.dia.backend.domain.model.Product;
import com.dia.backend.data.repository.*;
import com.dia.backend.domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataMapper implements CommandLineRunner {

  @Autowired MovieRepository movieRepository;
  @Autowired ProductRepository productRepository;

  @Autowired CustomerRepository customerRepository;

  @Autowired ScreeningRepository screeningRepository;

  @Autowired SeatRepository seatRepository;

  @Autowired KinoHallRepository kinoHallRepository;

  @Autowired TicketRepository ticketRepository;

  @Override
  public void run(String... args) throws Exception {

    Movie movie = new Movie();
    movie.setTitle("test MAJOR LONG TITLE HERE FOR THIS MOVIE");
    movie.setYear(2020);
    movie.setCountry("THIS IS THE COUNTRY OF THE MOVIE");
    movie.setLanguage("THIS LANGUAGE IS VERY VERY VERY LONG");
    movie.setDuration(120);
    movieRepository.save(movie);

    movie = new Movie();
    movie.setTitle("Die HARD1");
    movie.setYear(2004);
    movie.setCountry("USA!");
    movie.setLanguage("English");
    movie.setDuration(160);
    movieRepository.save(movie);

    for (int i = 0; i < 5; i++) {
      movie = new Movie();
      movie.setTitle("Die HARD" + i);
      movie.setYear(2004 + i);
      movie.setCountry("USA!");
      movie.setLanguage("English");
      movieRepository.save(movie);
    }

    movie = new Movie();
    movie.setTitle("Scooby DOO");
    movie.setYear(1997);
    movie.setCountry("USA!");
    movie.setLanguage("Indian English");
    movieRepository.save(movie);

    Product product = new Product();
    product.setProductName("Red Wine");
    product.setPrice(1500);
    product.setItemGroup("Red");
    productRepository.save(product);

    product = new Product();
    product.setProductName("White Wine");
    product.setPrice(1000);
    product.setItemGroup("White wine menu");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Ham sandwich ");
    product.setPrice(30);
    product.setItemGroup("Snacks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Tuna sandwich ");
    product.setPrice(30);
    product.setItemGroup("Snacks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Salmon sandwich ");
    product.setPrice(30);
    product.setItemGroup("Snacks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Not Popcorn");
    product.setPrice(30);
    product.setItemGroup("Snacks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Another white wine");
    product.setPrice(30);
    product.setItemGroup("White wine menu");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Latte");
    product.setPrice(30);
    product.setItemGroup("Hot Drinks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Machiatto");
    product.setPrice(30);
    product.setItemGroup("Hot Drinks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("TEST ERIK");
    product.setPrice(30);
    product.setItemGroup("New Drinks MENU");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Black");
    product.setPrice(30);
    product.setItemGroup("Hot Drinks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Cola");
    product.setPrice(30);
    product.setItemGroup("Cold Drinks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("White Russian");
    product.setPrice(65);
    product.setItemGroup("Hard Drinks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Ron B");
    product.setPrice(300);
    product.setItemGroup("Hard Drinks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Glenlivet 25y");
    product.setPrice(3000);
    product.setItemGroup("Hard Drinks");
    productRepository.save(product);

    KinoHall kinoHall = new KinoHall();
    kinoHall.setSeatRow(3);
    kinoHall.setSeat(4);
    kinoHallRepository.save(kinoHall);

    Screening screening = new Screening();
    screening.setKinoHall(kinoHall);
    screening.setMovie(movie);
    screeningRepository.save(screening);

    Seat seat = new Seat();
    seat.setScreening(screening);
    seatRepository.save(seat);

    Ticket ticket = new Ticket();
    //ticket.setCustomer(customer); // Remember to set this otherwise no: (Sets parent/child relationship)
    ticket.setSeat(seat);
    ticketRepository.save(ticket);

    Customer customer = new Customer();
    customer.setName("John");
    customer.setPhoneNumber("0011223344");
    customer.setTicket(ticket); // Remember to set this otherwise no: (Sets parent/child relationship)
    customerRepository.save(customer);


    // TODO FEJLER!
    // kinoHallRepository.save(new KinoHall());
  }
}
