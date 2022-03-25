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
    movie.setImagesrc("/img/moviecovers/licorice.jpg");
    // "https://m.media-amazon.com/images/M/MV5BYjkwMzIxYzMtOTVkMS00NDQxLThkMjItNzgxN2RiNjdlNTliXkEyXkFqcGdeQXVyODE5NzE3OTE@._V1_.jpg");
    movie.setTitle("Licorice Pizza");
    movie.setYear(2021);
    movie.setCountry("United States");
    movie.setLanguage("Engelsk");
    movie.setDuration(133);
    movie.setDirector("Paul Thomas Anderson");
    movie.setParentalGuide(7);
    movie.setTrailerLink("https://www.youtube.com/embed/ofnXPwUPENo");
    movieRepository.save(movie);

    Movie houseOfGucci = new Movie();
    houseOfGucci.setImagesrc("/img/moviecovers/gucci.jpg");
    // "https://m.media-amazon.com/images/M/MV5BYjkwMzIxYzMtOTVkMS00NDQxLThkMjItNzgxN2RiNjdlNTliXkEyXkFqcGdeQXVyODE5NzE3OTE@._V1_.jpg");
    houseOfGucci.setTitle("House of Gucci");
    houseOfGucci.setYear(2021);
    houseOfGucci.setCountry("United States");
    houseOfGucci.setLanguage("Engelsk");
    houseOfGucci.setDuration(158);
    houseOfGucci.setDirector("Ridley Scott");
    houseOfGucci.setParentalGuide(12);
    houseOfGucci.setTrailerLink("https://www.youtube.com/embed/pGi3Bgn7U5U");
    movieRepository.save(houseOfGucci);

    Movie france = new Movie();
    france.setImagesrc("/img/moviecovers/france.jpg");
    // "https://m.media-amazon.com/images/M/MV5BYjkwMzIxYzMtOTVkMS00NDQxLThkMjItNzgxN2RiNjdlNTliXkEyXkFqcGdeQXVyODE5NzE3OTE@._V1_.jpg");
    france.setTitle("France");
    france.setYear(2021);
    france.setCountry("France");
    france.setLanguage("Fransk");
    france.setDuration(133);
    france.setDirector("Bruno Dumont");
    france.setParentalGuide(15);
    france.setTrailerLink("https://www.youtube.com/embed/v6UvXtC0uw4");
    movieRepository.save(france);

    Movie flugt = new Movie();
    flugt.setImagesrc("/img/moviecovers/flugt.jpg");
    // "https://m.media-amazon.com/images/M/MV5BNGE1MWViOWYtZjg1NC00MWE4LWI4MDItNzBmYzNiMjg5ZTBlXkEyXkFqcGdeQXVyMDc0MzMwNA@@._V1_.jpg");

    flugt.setTitle("Flugt");
    flugt.setYear(2021);
    flugt.setCountry("Danmark");
    flugt.setLanguage("Dansk");
    flugt.setDuration(89);
    flugt.setDirector("Jonas Poher Rasmussen");
    flugt.setParentalGuide(14);
    flugt.setTrailerLink("https://www.youtube.com/embed/XcQV_hC9rMw");
    movieRepository.save(flugt);
    // --------------------------------------Product  below this
    // line----------------------------------------------------
    Product product = new Product();
    product.setProductName("El Señorito");
    product.setPrice(179);
    product.setItemGroup("Rødvin");
    productRepository.save(product);

    product = new Product();
    product.setProductName("PN");
    product.setPrice(279);
    product.setItemGroup("Rødvin");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Camino del Soto");
    product.setPrice(99);
    product.setItemGroup("Hvidvin");
    productRepository.save(product);

    product = new Product();
    product.setProductName("A Coroa");
    product.setPrice(149);
    product.setItemGroup("Hvidvin");
    productRepository.save(product);

    product = new Product();
    product.setProductName("100% Orange 0% So2, økologisk");
    product.setPrice(159);
    product.setItemGroup("Orangevin");

    productRepository.save(product);
    product = new Product();
    product.setProductName("Orange Ámfora, økologisk");
    product.setPrice(269);
    product.setItemGroup("Orangevin");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Braw: Beer me up scotty");
    product.setPrice(65);
    product.setItemGroup("Øl");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Braw: Friendship Juice");
    product.setPrice(65);
    product.setItemGroup("Øl");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Braw: Jingle my fucking bells");
    product.setPrice(65);
    product.setItemGroup("Øl");

    product = new Product();
    product.setProductName("Filter kaffe");
    product.setPrice(20);
    product.setItemGroup("Kaffe og the");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Cappuccino");
    product.setPrice(35);
    product.setItemGroup("Kaffe og the");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Kaffe latte");
    product.setPrice(35);
    product.setItemGroup("Kaffe og the");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Americano");
    product.setPrice(25);
    product.setItemGroup("Kaffe og the");
    productRepository.save(product);

    product = new Product();
    product.setProductName(
        "Naturfrisk: Sports, Lemon, Hindbær, Cola, Blodappelsin juice, Passion orange juice, "
            + " Danskvand m citrus");
    product.setPrice(30);
    product.setItemGroup("Sodavand og Juice");
    productRepository.save(product);

    productRepository.save(product);
    product = new Product();
    product.setProductName("Rawbar Daddelkonfekt Lakrids Ø Ganefryd");
    product.setPrice(20);
    product.setItemGroup("Snacks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Rawbar Daddelkonfekt Kakao Ø Ganefryd");
    product.setPrice(20);
    product.setItemGroup("Snacks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Rawbar Daddelkonfekt Kaffe Ø Ganefryd");
    product.setPrice(20);
    product.setItemGroup("Snacks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Rawbar Daddelkonfekt Mynthe Ø Ganefryd");
    product.setPrice(20);
    product.setItemGroup("Snacks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Mallows skumfiduser 3 stk");
    product.setPrice(45);
    product.setItemGroup("Snacks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Mallows med havsalt");
    product.setPrice(12);
    product.setItemGroup("Snacks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Mallows med lakrids og mørk chokolade");
    product.setPrice(12);
    product.setItemGroup("Snacks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Mallows saltkaramel og chokolade");
    product.setPrice(12);
    product.setItemGroup("Snacks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Summerbird Chokoladeboller");
    product.setPrice(20);
    product.setItemGroup("Snacks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Summerbird Chokoladeboller 2 stk");
    product.setPrice(35);
    product.setItemGroup("Snacks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Johan bülow Sweet Liquorice");
    product.setPrice(60);
    product.setItemGroup("Snacks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Johan bülow Salty Liquorice");
    product.setPrice(60);
    product.setItemGroup("Snacks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Haribo Matador mix");
    product.setPrice(35);
    product.setItemGroup("Snacks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Haribo Multi mix");
    product.setPrice(35);
    product.setItemGroup("Snacks");
    productRepository.save(product);

    product = new Product();
    product.setProductName("Haribo Click mix");
    product.setPrice(35);
    product.setItemGroup("Snacks");
    productRepository.save(product);

    // --------------------------------------------Screening
    // info---------------------------------------------------

    KinoHall salA = new KinoHall();
    salA.setSeatRows(3);
    salA.setSeatNumbers(5);
    salA.generateSeats();
    salA.setTitle("A");
    seatRepository.saveAll(salA.getSeats());
    kinoHallRepository.save(salA);

    KinoHall salB = new KinoHall();
    salB.setSeatRows(5);
    salB.setSeatNumbers(15);
    salB.generateSeats();
    salB.setTitle("B");
    seatRepository.saveAll(salB.getSeats());
    kinoHallRepository.save(salB);

    // ----------------------------------------------------Screening
    // info----------------------------------------------------

    Screening screening = new Screening(france, salA);
    screening.setStartTime(LocalDateTime.of(2022, 3, 21, 15, 0));
    screening.setEndTime(LocalDateTime.of(2022, 3, 21, 17, 30));
    screening.setMovie(movie);
    ticketRepository.saveAll(screening.generateTickets());
    screeningRepository.save(screening);

    screening = new Screening(houseOfGucci, salB);
    screening.setStartTime(LocalDateTime.of(2022, 3, 21, 15, 0));
    screening.setEndTime(LocalDateTime.of(2022, 3, 21, 17, 30));
    ticketRepository.saveAll(screening.generateTickets());
    screeningRepository.save(screening);

    screening = new Screening(flugt, salA);

    screening.setStartTime(LocalDateTime.of(2022, 3, 21, 18, 30));
    screening.setEndTime(LocalDateTime.of(2022, 3, 21, 20, 0));
    ticketRepository.saveAll(screening.generateTickets());
    screeningRepository.save(screening);

    screening = new Screening(houseOfGucci, salB);
    screening.setStartTime(LocalDateTime.of(2022, 3, 21, 18, 30));
    screening.setEndTime(LocalDateTime.of(2022, 3, 21, 20, 0));
    ticketRepository.saveAll(screening.generateTickets());
    screeningRepository.save(screening);

    screening = new Screening(france, salA);
    screening.setStartTime(LocalDateTime.of(2022, 3, 21, 21, 0));
    screening.setEndTime(LocalDateTime.of(2022, 3, 21, 23, 0));
    ticketRepository.saveAll(screening.generateTickets());
    screeningRepository.save(screening);

    screening = new Screening(movie, salB);
    screening.setStartTime(LocalDateTime.of(2022, 3, 21, 21, 0));
    screening.setEndTime(LocalDateTime.of(2022, 3, 21, 23, 0));
    ticketRepository.saveAll(screening.generateTickets());
    screeningRepository.save(screening);

    screening = new Screening(movie, salB);
    screening.setStartTime(LocalDateTime.of(2022, 3, 9, 15, 0));
    screening.setEndTime(LocalDateTime.of(2022, 3, 9, 17, 30));
    screening.setEvent("Senior Onsdag");
    ticketRepository.saveAll(screening.generateTickets());
    screeningRepository.save(screening);

    screening = new Screening(france, salB);
    screening.setStartTime(LocalDateTime.of(2022, 3, 25, 18, 30));
    screening.setEndTime(LocalDateTime.of(2022, 3, 25, 20, 0));
    screening.setEvent("Fransk fredag");
    ticketRepository.saveAll(screening.generateTickets());
    screeningRepository.save(screening);

    screening = new Screening(houseOfGucci, salA);
    screening.setStartTime(LocalDateTime.of(2022, 3, 28, 18, 30));
    screening.setEndTime(LocalDateTime.of(2022, 3, 28, 20, 0));
    ticketRepository.saveAll(screening.generateTickets());
    screeningRepository.save(screening);

    screening = new Screening(flugt, salB);
    screening.setStartTime(LocalDateTime.of(2022, 3, 28, 18, 30));
    screening.setEndTime(LocalDateTime.of(2022, 3, 28, 20, 0));
    ticketRepository.saveAll(screening.generateTickets());
    screeningRepository.save(screening);

    // ---------------------------------------------- Customer
    // info---------------------------------------------------------
    Customer customer = new Customer();
    customer.setName("John");
    customer.setPhoneNumber("0011223344");
    screening.getTickets().get(1).setCustomer(customer);
    customerRepository.save(customer);
    ticketRepository.save(screening.getTickets().get(1));
  }
}
