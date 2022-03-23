package com.dia.backend.view.controller;

import com.dia.backend.data.repository.CustomerRepository;
import com.dia.backend.domain.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/customers") // prefix for endpoints
public class CustomerController {

  @Autowired CustomerRepository customerRepository;

  @GetMapping("")
  public List<Customer> displayCustomers() {
    return customerRepository.findAll();
  }

  @GetMapping("/{id}")
  public Customer findCustomerById(@PathVariable int id) {
    Optional<Customer> customer = customerRepository.findById(id);
    if (customer.isPresent()) {
      return customer.get();
    } else {
      return null;
    }
  }

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public Customer postCustomer(@RequestBody Customer customer) {
    System.out.println(customer.getName() + customer.getPhoneNumber());
    return customerRepository.save(customer);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Customer> updateCustomer(
      @PathVariable int id, @RequestBody Customer customer) {
    Optional<Customer> customer1 = customerRepository.findById(id);
    if (customer1.isPresent()) {
      customerRepository.save(customer);
      return new ResponseEntity<>(customer, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
    try {
      customerRepository.deleteById(id);
      return new ResponseEntity<>("Deleted: " + id, HttpStatus.OK);
    } catch (Exception err) {
      return new ResponseEntity<>("Error deleting: " + id, HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/phonenumber={phoneNumber}")
  public Customer findCustomerById(@PathVariable String phoneNumber) {
    phoneNumber = phoneNumber.replaceAll("[{-}]", "");
    System.out.println(phoneNumber);
    Optional<Customer> customer = customerRepository.findByPhoneNumber(phoneNumber);
    if (customer.isPresent()) {
      return customer.get();
    } else {
      return null;
    }
  }
}
