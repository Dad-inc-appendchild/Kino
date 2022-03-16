package com.dia.backend.domain.model;

import javax.persistence.*;

@Entity
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int customerId;
  private String name;
  private String phoneNumber;

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
