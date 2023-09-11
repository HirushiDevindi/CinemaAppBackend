package com.example.CinemaAppBackend.Dto;

import java.time.LocalDate;
import java.util.List;

public class MovieDTO {

    private String name;
    private  String language;
    private  String country;
    //private String releaseDate;
    private LocalDate releaseDate;
    private String movieDuration;

//    private List<String> showDays;
//
//    private String imgUrl;
//    private List<List<String>> showTimes;


    public MovieDTO() {
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

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(String movieDuration) {
        this.movieDuration = movieDuration;
    }

    @Override
    public String toString() {
        return "MovieDTO{" +
                "name='" + name + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", movieDuration='" + movieDuration + '\'' +
                '}';
    }
}
