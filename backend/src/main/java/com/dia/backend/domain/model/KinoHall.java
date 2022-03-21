package com.dia.backend.domain.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class KinoHall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kinoHallId")
    private int kinoHallId;

    @OneToMany
    @JoinColumn(name = "kinohall_id")
    private List<Seat> seats;

    private int seatRows; // Cant be named row because its a reserved keyword
    private int seatNumbers;

    public KinoHall() {
    }

    public KinoHall(int seatRows, int seatNumbers) {
        this.seatRows = seatRows;
        this.seatNumbers = seatNumbers;
        generateSeats();
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public int getSeatRows() {
        return seatRows;
    }

    public void setSeatRows(int row) {
        this.seatRows = row;
    }

    public int getSeatNumbers() {
        return seatNumbers;
    }

    public void setSeatNumbers(int seat) {
        this.seatNumbers = seat;
    }

    public int getKinoHallId() {
        return kinoHallId;
    }

    public void setKinoHallId(int kinoHallId) {
        this.kinoHallId = kinoHallId;
    }

    public List<Seat> generateSeats(){
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= getSeatRows(); i++){
            for(int j = 1; j <=getSeatNumbers(); j++){
                seats.add(new Seat(i,j));
            }
        }
        setSeats(seats);
        return seats;
    }
}
