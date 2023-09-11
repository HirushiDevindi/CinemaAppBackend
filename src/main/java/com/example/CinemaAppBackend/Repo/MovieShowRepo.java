package com.example.CinemaAppBackend.Repo;

import com.example.CinemaAppBackend.Entity.Movie;
import com.example.CinemaAppBackend.Entity.MovieShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieShowRepo extends JpaRepository<MovieShow, Long>{
    List<MovieShow> findAllByMovieMovieId(Long movieId);
    void deleteAllByMovieMovieId(Long movieId);

    MovieShow findByDateAndStartTimeAndEndTime(LocalDate date, String startTime, String endTime);
}


