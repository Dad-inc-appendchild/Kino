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
    /*movie.setImagesrc(
        "https://m.media-amazon.com/images/M/MV5BMjkwYzU5NWMtNDBmZS00ZGQ4LThjNjMtN2Y4NzViMGM0ODE2XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_.jpg");
    movie.setTitle("Belfast");
    movie.setYear(2020);
    movie.setCountry("THIS IS THE COUNTRY OF THE MOVIE");
    movie.setLanguage("THIS LANGUAGE IS VERY VERY VERY LONG");
    movie.setDuration(120);
    movieRepository.save(movie);*/

    movie = new Movie();
    movie.setImagesrc(
            "https://m.media-amazon.com/images/M/MV5BZTYwMjRkZWUtZWFhZC00OWQ0LTlkOGMtZGRmODllZjg1MWJiXkEyXkFqcGdeQXVyNDczMTY0NjM@._V1_FMjpg_UY576_.jpg");
    movie.setTitle("Rose");
    movie.setYear(2022);
    movie.setCountry("Danmark");
    movie.setLanguage("Dansk");
    movie.setDuration(106);
    movie.setTrailerLink("https://youtu.be/QJUzZ0uIwts");
    movieRepository.save(movie);

    movie = new Movie();
    movie.setImagesrc(
        "https://m.media-amazon.com/images/M/MV5BMTdkZDc4YmQtYzA3My00NzliLThjN2YtMDIwMDkxY2Y4ODdmXkEyXkFqcGdeQXVyMTA2MDU0NjM5._V1_.jpg");
    movie.setTitle("Spence");
    movie.setYear(2021);
    movie.setCountry("Amerikansk");
    movie.setLanguage("Engelsk");
    movie.setDuration(117);
    movie.setTrailerLink("https://www.youtube.com/watch?v=WllZh9aekDg");
    movieRepository.save(movie);

    movie = new Movie();
    movie.setImagesrc("https://www.paradisbio.dk/data/TempFolderLongTerm//mediacache_4842814fa8fc6f19b5ef97b505a69b19.jpg");
            //"https://m.media-amazon.com/images/M/MV5BYTdmNzMwNzEtNDQyZi00M2VmLWFiMzktZmQ5MTMxYjMxMjg2XkEyXkFqcGdeQXVyMjQ0NzcxNjM@._V1_.jpg");
    movie.setTitle("Sneleoparden");
    movie.setYear(2021);
    movie.setCountry("Frankrig");
    movie.setLanguage("Engelsk");
    movie.setDuration(92);
    movie.setTrailerLink("https://www.youtube.com/watch?v=DzGAUO5WiCE");
    movieRepository.save(movie);

    movie = new Movie();
    movie.setImagesrc(
            "https://m.media-amazon.com/images/M/MV5BNWI4NDMxZjctYjcxMy00ZWNhLWFlOTctNjhiZTZiMjQ0ZGNjXkEyXkFqcGdeQXVyMzcwNjA5Mzg@._V1_.jpg");
    movie.setTitle("Speak no evil");
    movie.setYear(2022);
    movie.setCountry("Dansk");
    movie.setLanguage("Dansk/Engelsk");
    movie.setDuration(98);
    movie.setTrailerLink("https://www.youtube.com/watch?v=eAEw-8d1HsE");
    movieRepository.save(movie);


    /*for (int i = 0; i < 7; i++) {     Loop to generate more movies
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
    }*/

    //--------------------------------------Product  below this line----------------------------------------------------
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
    product.setProductName("Naturfrisk: Sports, Lemon, Hindbær, Cola, Blodappelsin juice, Passion orange juice,  Danskvand m citrus");
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

    //--------------------------------------------Screening info--------------------------------------------------------

    KinoHall kinoHall = new KinoHall();
    kinoHall.setSeatRows(3);
    kinoHall.setSeatNumbers(4);
    kinoHall.generateSeats();
    seatRepository.saveAll(kinoHall.getSeats());
    kinoHallRepository.save(kinoHall);

    Screening screening = new Screening(movie, kinoHall);
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
