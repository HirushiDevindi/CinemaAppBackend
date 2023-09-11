package com.example.CinemaAppBackend.Entity;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="movie") //table to store data about movie
public class Movie {
    @Id
    @Column(name="movieId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long movieId;

    @Column(name="name")
    private String name;
    @Column(name="language")
    private  String language;
    @Column(name="country")
    private  String country;
    @Column(name="duration")
    private  String movieDuration;
    @Column(name="releaseDate")
    //private String releaseDate;
    private LocalDate releaseDate;


    public Movie() {
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public  void setCountry(String country) {
        this.country = country;
    }

    public String getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(String movieDuration) {
        this.movieDuration = movieDuration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
