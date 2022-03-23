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

import java.time.LocalDateTime;

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
    movie.setImagesrc(
        "https://m.media-amazon.com/images/M/MV5BMjkwYzU5NWMtNDBmZS00ZGQ4LThjNjMtN2Y4NzViMGM0ODE2XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_.jpg");
    movie.setTitle("Belfast");
    movie.setYear(2020);
    movie.setCountry("THIS IS THE COUNTRY OF THE MOVIE");
    movie.setLanguage("THIS LANGUAGE IS VERY VERY VERY LONG");
    movie.setDuration(120);
    movieRepository.save(movie);

    movie = new Movie();
    movie.setImagesrc(
        "https://m.media-amazon.com/images/M/MV5BYzE5MGRjMjUtYzQwYy00NGU2LTk2ZjYtMDJhNDM0MmQ3MTc0XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_.jpg");
    movie.setTitle("Another round");
    movie.setYear(1997);
    movie.setCountry("Dansk!");
    movie.setLanguage("Dansk");
    movieRepository.save(movie);

    for (int i = 0; i < 7; i++) {
      movie = new Movie();
      movie.setImagesrc(
          "https://m.media-amazon.com/images/M/MV5BZmYzNTBiZTAtNmJjNi00MTcyLThlZGMtNDA5Y2RkNjM3ODAzXkEyXkFqcGdeQXVyMzg3OTQ5MjU@._V1_.jpg");
      movie.setTitle("Test data " + i);
      movie.setYear(2004 + i);
      movie.setCountry("USA!");
      movie.setLanguage("English");
      movieRepository.save(movie);

      movie = new Movie();
      movie.setImagesrc(
          "https://m.media-amazon.com/images/M/MV5BMzNhOTdlNmUtYzNiYi00MmUxLTg3ZjgtZjk4Y2Y5YTk3ODdiXkEyXkFqcGdeQXVyMTE2MjAzMTU3._V1_.jpg");
      movie.setTitle("Generisk data " + i);
      movie.setYear(2004);
      movie.setCountry("USA!");
      movie.setLanguage("English");
      movie.setDuration(160);
      movieRepository.save(movie);
    }
    Product product = new Product();
    product.setProductName("Gaja, Sito Moresco 2017");
    product.setPrice(1500);
    product.setItemGroup("Rødvin");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Mitolo, 7th Son Grenache Shiraz 2018");
    product.setPrice(1000);
    product.setItemGroup("Rødvin");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Weingut Egon Müller, Riesling Spätlese Scharzhofberger 2020");
    product.setPrice(425);
    product.setItemGroup("Hvidvin");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Mangalica skinke ");
    product.setPrice(45);
    product.setItemGroup("Snacks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Røgede andalusiske mandler ");
    product.setPrice(30);
    product.setItemGroup("Snacks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Blandede oliven ");
    product.setPrice(30);
    product.setItemGroup("Snacks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Summerbird chokolade 5 stk");
    product.setPrice(50);
    product.setItemGroup("Snacks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Summerbird flødeboller 2 stk");
    product.setPrice(45);
    product.setItemGroup("Snacks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Johan bülow lakridser");
    product.setPrice(55);
    product.setItemGroup("Snacks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Mallows saltkaramel og belgisk chokolade");
    product.setPrice(45);
    product.setItemGroup("Snacks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Alex Gambal, Batard-Montrachet Grand Cru 2018");
    product.setPrice(325);
    product.setItemGroup("Hvidvin");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Latte");
    product.setPrice(30);
    product.setItemGroup("Varme drikke");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Machiatto");
    product.setPrice(30);
    product.setItemGroup("Varme drikke");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Americano");
    product.setPrice(30);
    product.setItemGroup("Varme drikke");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Cola, Fanta, Sprite, Danskvand");
    product.setPrice(30);
    product.setItemGroup("Kolde drikke");
    productRepository.save(product);

    product = new Product();
    product.setProductName("White Russian");
    product.setPrice(65);
    product.setItemGroup("Sprut");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Ron B");
    product.setPrice(300);
    product.setItemGroup("Sprut");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Glenlivet 25y");
    product.setPrice(3000);
    product.setItemGroup("Sprut");
    productRepository.save(product);

    KinoHall kinoHall = new KinoHall();
    kinoHall.setSeatRows(3);
    kinoHall.setSeatNumbers(4);
    kinoHall.generateSeats();
    seatRepository.saveAll(kinoHall.getSeats());
    kinoHallRepository.save(kinoHall);

    movie = new Movie();
    movie.setImagesrc(
        "https://m.media-amazon.com/images/M/MV5BZmYzNTBiZTAtNmJjNi00MTcyLThlZGMtNDA5Y2RkNjM3ODAzXkEyXkFqcGdeQXVyMzg3OTQ5MjU@._V1_.jpg");
    movie.setTitle("Borte med blæsten");
    movie.setYear(2004);
    movie.setCountry("USA");
    movie.setLanguage("Dansk");
    movieRepository.save(movie);

    Screening screening = new Screening(movie, kinoHall);
    screening.setStartTime(LocalDateTime.of(2022, 3, 21, 15, 0));
    screening.setEndTime(LocalDateTime.of(2022, 3, 21, 17, 30));
    screening.setEvent("Senior Onsdag");
    screening.setMovie(movie);
    ticketRepository.saveAll(screening.generateTickets());
    screeningRepository.save(screening);

    screening = new Screening(movie, kinoHall);
    screening.setStartTime(LocalDateTime.of(2022, 3, 9, 15, 0));
    screening.setEndTime(LocalDateTime.of(2022, 3, 9, 17, 30));
    screening.setEvent("Senior Onsdag");
    ticketRepository.saveAll(screening.generateTickets());
    screeningRepository.save(screening);

    screening = new Screening(movie, kinoHall);
    screening.setStartTime(LocalDateTime.of(2022, 3, 21, 18, 30));
    screening.setEndTime(LocalDateTime.of(2022, 3, 21, 20, 0));
    ticketRepository.saveAll(screening.generateTickets());
    screeningRepository.save(screening);

    Customer customer = new Customer();
    customer.setName("John");
    customer.setPhoneNumber("0011223344");
    screening.getTickets().get(1).setCustomer(customer);
    customerRepository.save(customer);
    ticketRepository.save(screening.getTickets().get(1));
  }
}
