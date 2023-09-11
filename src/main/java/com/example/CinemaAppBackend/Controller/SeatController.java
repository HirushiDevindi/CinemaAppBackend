package com.example.CinemaAppBackend.Controller;


import com.example.CinemaAppBackend.Dto.SeatDTO;
import com.example.CinemaAppBackend.Entity.MovieShow;
import com.example.CinemaAppBackend.Entity.Seat;
import com.example.CinemaAppBackend.Service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping
@CrossOrigin( allowedHeaders = {})
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping("/seat")// to add a seat entry
    public ResponseEntity<Map<String, Object>> addSeat(@RequestBody SeatDTO seatDTO){
        Seat temp = new Seat();
        try{
            return seatService.addSeat(seatDTO);
        }catch (Exception e){
            System.out.println(e);

        }
        return null;
    }


    @PutMapping("/seat/{id}") // to edit details of seat entry
    public ResponseEntity<Map<String, Object>>  editSeat(@PathVariable("id") Long id, @RequestBody SeatDTO seatDTO){
        Seat temp = new Seat();
        try{
            return seatService.editSeat(seatDTO, id);
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    @GetMapping("/seat") //get all seat alone with show terms
    public List<Seat> getAllSeatOfShowTerms(){
        return seatService.getAllSeatOfShowTerms();
    }

    @GetMapping("/seat/{id}") // get a seat entry with movie show term
    public List<Seat> findSeatByMovieShowId(@PathVariable("id") Long id){
        return seatService.findSeatByMovieShowId(id);
    }

//    @GetMapping("/seat/{id}") // get a seat entry with movie by movie id
//    public List<Seat> findSeatByMovieId(@PathVariable("id") Long id){
//        return seatService.findSeatByMovieId(id);
//    }

    @DeleteMapping("/seat/{id}") // delete a seat entry
    public String deleteSeat(@PathVariable("id") long id){
        return seatService.deleteSeat(id);

    }

    @GetMapping("/seat/seatId/{id}")// get a seat entry by seat id
    public Optional<Seat> getSeat(@PathVariable("id") long id){
        return seatService.getSeat(id);
    }



}
