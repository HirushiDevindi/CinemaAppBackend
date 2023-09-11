package com.example.CinemaAppBackend.Controller;


import com.example.CinemaAppBackend.Dto.MovieDTO;
import com.example.CinemaAppBackend.Entity.Movie;
import com.example.CinemaAppBackend.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping
@CrossOrigin(  allowedHeaders = {})
public class MovieController {


    @Autowired
    private MovieService movieService;


    @PostMapping("/movie") //create a new movie
    public ResponseEntity<Map<String, Object>> addMovie(@RequestBody MovieDTO movieDTO){
        Movie movieEntity = new Movie();
        try{
            return movieService.addMovie(movieDTO);
        } catch (Exception e){
            System.out.println(e);
        }
        //return movieEntity;
        return null;
    }

    @PutMapping("/movie/{id}") // update/edit an existing movie
    public ResponseEntity<Map<String, Object>> editMovie(@PathVariable("id") Long id,@RequestBody MovieDTO movieDTO) {
        Movie movieEntity = new Movie();
        try{
            return movieService.updateMovie(movieDTO,id);
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    @GetMapping("/movie") //Get list of all the movies
    public List<Movie> getMovies(){
        return movieService.getMovies();
    }

    @GetMapping("/movie/{id}")// to get a movie
    public Optional<Movie> getMovie(@PathVariable("id") long id){
        return movieService.getMovie(id);
    }

    @DeleteMapping("/movie/{id}") //Delete a movie
    public String deleteMovie(@PathVariable("id") Long id){
        return movieService.deleteMovie(id);
    }

}
