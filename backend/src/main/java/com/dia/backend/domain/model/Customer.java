package com.dia.backend.domain.model;

import javax.persistence.*;

@Entity
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column
  private Long customerId;
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

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long id) {
    this.customerId = id;
  }
}
