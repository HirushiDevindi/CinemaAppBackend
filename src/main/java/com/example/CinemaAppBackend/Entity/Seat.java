package com.example.CinemaAppBackend.Entity;

import javax.persistence.*;

@Entity
@Table(name="seat")
public class Seat {

    @Id
    @Column(name="seatId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seatId;

    @Column(name="totalSeat")
    private long totalSeat;
    @Column(name="availableSeat")
    private long availableSeat;

    @ManyToOne
    @JoinColumn(name = "movieShowId")
    //  @JsonBackReference
    private  MovieShow movieShow;


    public Seat() {
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

    public MovieShow getMovieShow() {
        return movieShow;
    }

    public void setMovieShow(MovieShow movieShow) {
        this.movieShow = movieShow;
    }
}
