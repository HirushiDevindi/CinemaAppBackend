package com.example.CinemaAppBackend.Dto;

import com.example.CinemaAppBackend.Entity.MovieShow;

public class SeatDTO {

    private long seatId;
    private long totalSeat;
    private long availableSeat;

    private long movieShowId;

    public SeatDTO() {
    }

    public long getSeatId() {
        return seatId;
    }

    public void setSeatId(long seatId) {
        this.seatId = seatId;
    }

    public long getTotalSeat() {
        return totalSeat;
    }

    public void setTotalSeat(long totalSeat) {
        this.totalSeat = totalSeat;
    }

    public long getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(long availableSeat) {
        this.availableSeat = availableSeat;
    }

    public long getMovieShowId() {
        return movieShowId;
    }

    public void setMovieShowId(long movieShowId) {
        this.movieShowId = movieShowId;
    }

    @Override
    public String toString() {
        return "SeatDTO{" +
                "seatId=" + seatId +
                ", totalSeat=" + totalSeat +
                ", availableSeat=" + availableSeat +
                ", movieShowId=" + movieShowId +
                '}';
    }
}
