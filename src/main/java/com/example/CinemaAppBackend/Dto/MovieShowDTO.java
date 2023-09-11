package com.example.CinemaAppBackend.Dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class MovieShowDTO {

    private long movieShowId;
//    private  String date;
    private LocalDate date;
    private String startTime;
    private String endTime;
    private long movieId;


    public MovieShowDTO() {
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

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return "MovieShow{" +
                "movieShowId=" + movieShowId +
                ", date='" + date + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", movieId=" + movieId +
                '}';
    }
}
