package com.example.CinemaAppBackend.Entity;

public class Booking {

    private Long seatId;
    private Long numberOfSeats;

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public Long getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Long numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "seatId=" + seatId +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }
}
