package com.example.CinemaAppBackend.Controller;


import com.example.CinemaAppBackend.Dto.MovieShowDTO;
import com.example.CinemaAppBackend.Entity.Movie;
import com.example.CinemaAppBackend.Entity.MovieShow;
import com.example.CinemaAppBackend.Service.MovieShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping
@CrossOrigin( allowedHeaders = {}) // Allowing requests from any origin with any headers
public class MovieShowController {

    public final MovieShowService movieShowService;
    @Autowired
    public MovieShowController(MovieShowService movieShowService) {
        this.movieShowService = movieShowService;
    }

    @PostMapping("/show") //to create a new show term
    public ResponseEntity<Map<String, Object>> addShow(@RequestBody MovieShowDTO movieShowDTO){
        MovieShow temp =new MovieShow();
        try{
            return movieShowService.addShow(movieShowDTO);
        } catch (Exception e){
            System.out.println(e);

        }
        return null;

    }

    @PutMapping("/show/{movieShowId}") // update an existing show term details
    public ResponseEntity<Map<String, Object>> editShow(@PathVariable("movieShowId") Long id, @RequestBody MovieShowDTO movieShowDTO){
        MovieShow temp =new MovieShow();
        try{
            return movieShowService.editShow(movieShowDTO, id);
        } catch (Exception e){
            System.out.println(e);

        }
        return null;

    }

    @GetMapping("/show") //get all show terms
    public List<MovieShow> getAllShowTerms(){
        return movieShowService.getAllShowTerms();
    }

    @GetMapping("/show/{id}") // get the show terms allocated for a movie by movie_id
    public List<MovieShow> findShowsByMovieId(@PathVariable Long id) {
        return movieShowService.findShowsByMovieId(id);

    }

    @DeleteMapping("/show/{id}") // delete a show term by id
    public String deleteShow(@PathVariable("id") long id){
        return movieShowService.deleteShow(id);

    }

    //--
    @GetMapping("/show/movieShow/{id}")// to get a movieShow by movie show id
    public Optional<MovieShow> getMovie(@PathVariable("id") long id){
        return movieShowService.getMovieShow(id);
    }




}
