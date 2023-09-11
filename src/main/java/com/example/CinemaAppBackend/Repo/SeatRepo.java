package com.example.CinemaAppBackend.Repo;

import com.example.CinemaAppBackend.Entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SeatRepo extends JpaRepository<Seat, Long> {

    List<Seat> findAllByMovieShowMovieShowId(Long movieShowId);

    //Optional<Seat> findByMovieShowId(Long movieShowId);

    Optional<Seat> findByMovieShowMovieShowId(Long movieShowId);

//    Optional<Seat> findByMovieShowId(Long movieShowId);

    //List<Seat> findAllByMovieId(Long id);

    //List<Seat> findAllByMovieMovieId(Long id);
}
