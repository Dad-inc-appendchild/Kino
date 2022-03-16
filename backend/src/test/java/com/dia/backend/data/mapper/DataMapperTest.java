package com.dia.backend.data.mapper;

import com.dia.backend.data.repository.CustomerRepository;
import com.dia.backend.domain.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.List;
/*
class DataMapperTest implements CommandLineRunner {
  @Autowired
  CustomerRepository customerRepository;

  @Test
  void givenCustomerNameIsJohnExpectJohn() {

    List<Customer> customers = customerRepository.findAll();

    System.out.println(customers);
    Assertions.assertFalse(customers.isEmpty());
  }

  @Override
  public void run(String... args) throws Exception {
    Customer customer = new Customer();
    customer.setName("John");
    customer.setPhoneNumber("0011223344");
    customerRepository.save(customer);
  }
}
*/