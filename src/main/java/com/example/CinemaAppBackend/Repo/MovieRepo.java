package com.example.CinemaAppBackend.Repo;

import com.example.CinemaAppBackend.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepo extends JpaRepository<Movie,Long> {


    Optional<Movie> findById(Long id);

    Movie findByName(String name);
}
