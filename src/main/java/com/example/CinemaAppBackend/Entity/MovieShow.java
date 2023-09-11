package com.example.CinemaAppBackend.Entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class MovieShow {
    @Id
    @Column(name="movieShowId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long movieShowId;

    @Column(name="date")
//    private  String date;
    private LocalDate date;
    @Column(name = "startTime")
    private String startTime;
    @Column(name = "endTIme")
    private String endTime;


    @ManyToOne
    @JoinColumn(name = "movieId")
    //  @JsonBackReference
    private  Movie movie;

    public MovieShow() {
    }

    public long getMovieShowId() {
        return movieShowId;
    }

    public void setMovieShowId(long movieShowId) {
        this.movieShowId = movieShowId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
