package com.dia.backend.domain.model;

import javax.persistence.*;

@Entity
public class Movie {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column
  private String title;

  private int year;

  private String country;

  private String language;

  private int duration;

  private int director;

  private int parentalGuide;

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public int getDirector() {
    return director;
  }

  public void setDirector(int director) {
    this.director = director;
  }

  public int getParentalGuide() {
    return parentalGuide;
  }

  public void setParentalGuide(int parentalGuide) {
    this.parentalGuide = parentalGuide;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }
}
