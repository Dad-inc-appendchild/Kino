package com.dia.backend.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="customerId")
  private int customerId;
  private String name;
  private String phoneNumber;

  @OneToMany
  @JoinColumn(name="customerId")
  @JsonBackReference
  private Set<Ticket> tickets = new HashSet<>();

  public Set<Ticket> getTickets() {
    return tickets;
  }

  public void setTickets(Set<Ticket> tickets) {
    this.tickets = tickets;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int id) {
    this.customerId = id;
  }
}
