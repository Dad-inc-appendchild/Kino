package com.dia.backend.domain.model;

import java.util.List;

public class CustomerAndTIckersWrappper {
  public Customer customer;
  public List<Ticket> tickets;

  public Customer getCustomer() {
    return customer;
  }

  public List<Ticket> getTickets() {
    return tickets;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public void setTickets(List<Ticket> tickets) {
    this.tickets = tickets;
  }
}
