package com.dia.backend.domain.model;

import javax.persistence.*;

@Entity
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="customerId")
  private int customerId;
  private String name;
  private String phoneNumber;

  @ManyToOne
  @JoinColumn(name = "ticketId")
  //@JsonBackReference -> this feature closes the stream
  private Ticket ticket;

  public Ticket getTicket() {
    return ticket;
  }

  public void setTicket(Ticket ticket) {
    this.ticket = ticket;
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
