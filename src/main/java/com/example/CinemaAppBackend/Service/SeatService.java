package com.example.CinemaAppBackend.Service;


import com.example.CinemaAppBackend.Dto.SeatDTO;
import com.example.CinemaAppBackend.Entity.MovieShow;
import com.example.CinemaAppBackend.Entity.Seat;
import com.example.CinemaAppBackend.Repo.MovieShowRepo;
import com.example.CinemaAppBackend.Repo.SeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SeatService {

    @Autowired
    private SeatRepo seatRepo;

    @Autowired
    private MovieShowRepo movieShowRepo;

    public ResponseEntity<Map<String, Object>> addSeat(SeatDTO seatDTO){

        Optional<Seat> existingSeat = seatRepo.findByMovieShowMovieShowId(seatDTO.getMovieShowId());

        if (existingSeat.isPresent()) {
            // MovieShow with this ID already exists in Seat table, so don't add it again
            return ResponseEntity.ok(
                    new HashMap<String, Object>() {{
                        put("message", "Movie show is already added");
                        put("seat", null);
                    }}
            );
        }

        System.out.println("Received totalSeat: " + seatDTO.getTotalSeat());
        System.out.println("Received availableSeat: " + seatDTO.getAvailableSeat());
        System.out.println("Received movieShowId: " + seatDTO.getMovieShowId());

        Seat temp = new Seat();

        if(seatDTO.getTotalSeat()<=0){
            return ResponseEntity.ok(
                    new HashMap<String, Object>() {{
                        put("message", "Enter a valid number for total seats");
                        put("seat", null);
                    }}
            );
        }

        if(seatDTO.getAvailableSeat()<0){
            return ResponseEntity.ok(
                    new HashMap<String, Object>() {{
                        put("message", "Enter a valid number for available seats");
                        put("seat", null);
                    }}
            );
        }
        if(seatDTO.getAvailableSeat()>seatDTO.getTotalSeat()){
            return ResponseEntity.ok(
                    new HashMap<String, Object>() {{
                        put("message", "Available seats number cannot exceed the total number of seats");
                        put("seat", null);
                    }}
            );
        }

        temp.setTotalSeat(seatDTO.getTotalSeat());
        temp.setAvailableSeat(seatDTO.getAvailableSeat());

        Optional movieShow = movieShowRepo.findById(seatDTO.getMovieShowId());
        if(movieShow.isPresent()){
            MovieShow movieShowEntity = (MovieShow) movieShow.get();
            LocalDate currentDate = LocalDate.now();

            if(movieShowEntity.getDate().isBefore(currentDate)){
                return ResponseEntity.ok(
                        new HashMap<String, Object>() {{
                            put("message", "Movie show is already showed");
                            put("seat", null);
                        }}
                );
            }
            temp.setMovieShow(movieShowEntity);
        }
        seatRepo.save(temp);
        return ResponseEntity.ok(
                new HashMap<String, Object>() {{
                    put("message", "Seat entry is added successfully");
                    put("movieShow", temp);
                }}
        );
    }


    public ResponseEntity<Map<String, Object>> editSeat(SeatDTO seatDTO, Long id) {


        Optional<Seat> existingSeat = seatRepo.findByMovieShowMovieShowId(seatDTO.getMovieShowId());

        if (existingSeat.isPresent() && existingSeat.get().getSeatId()!=id){
            // MovieShow with this ID already exists in Seat table and it's not the current seat, so don't update it
            return ResponseEntity.ok(
                    new HashMap<String, Object>() {{
                        put("message", "Movie show is already added");
                        put("seat", null);
                    }}
            );
        }

        Seat temp = new Seat();
        Optional seatOptional = seatRepo.findById(id);
        if(seatOptional.isPresent()){
            temp = (Seat) seatOptional.get();
        }

        if(seatDTO.getTotalSeat()<=0){
            return ResponseEntity.ok(
                    new HashMap<String, Object>() {{
                        put("message", "Enter a valid number for total seats");
                        put("seat", null);
                    }}
            );
        }

        if(seatDTO.getAvailableSeat()<0){
            return ResponseEntity.ok(
                    new HashMap<String, Object>() {{
                        put("message", "Enter a valid number for available seats");
                        put("seat", null);
                    }}
            );
        }

        if(seatDTO.getAvailableSeat()>seatDTO.getTotalSeat()){
            return ResponseEntity.ok(
                    new HashMap<String, Object>() {{
                        put("message", "Available seats number cannot exceed the total number of seats");
                        put("seat", null);
                    }}
            );
        }

        if(seatDTO.getTotalSeat()!=0){
            temp.setTotalSeat(seatDTO.getTotalSeat());
        }
        if(seatDTO.getAvailableSeat() !=0){
            temp.setAvailableSeat(seatDTO.getAvailableSeat());
        }
        if(seatDTO.getMovieShowId()!=0){


            Optional movieShow = movieShowRepo.findById(seatDTO.getMovieShowId());
            if(movieShow.isPresent()){
                MovieShow movieShowEntity = (MovieShow) movieShow.get();
                LocalDate currentDate = LocalDate.now();

                if(movieShowEntity.getDate().isBefore(currentDate)){
                    return ResponseEntity.ok(
                            new HashMap<String, Object>() {{
                                put("message", "Movie show is already showed");
                                put("seat", null);
                            }}
                    );
                }
                temp.setMovieShow(movieShowEntity);
            }
            seatDTO.setMovieShowId(seatDTO.getMovieShowId());
        }



        temp.setSeatId(id);
        seatRepo.save(temp);
        Seat finalTemp = temp;
        return ResponseEntity.ok(
                new HashMap<String, Object>() {{
                    put("message", "Seat entry is updated successfully");
                    put("seat", finalTemp);
                }}
        );

    }


    public List<Seat> getAllSeatOfShowTerms() {
        return seatRepo.findAll();
    }


    public List<Seat> findSeatByMovieShowId(Long id) {
        //return seatRepo.findAllByMovieShowMovieShowId(id);
        return seatRepo.findAllByMovieShowMovieShowId(id);

    }


    public String deleteSeat(long id) {
        seatRepo.deleteById(id);
        return id + " deleted";
    }

    public Optional<Seat> getSeat(long id) {
        return  seatRepo.findById(id);
    }


//    public List<Seat> findSeatByMovieId(Long id) {
//        return seatRepo.findAllByMovieMovieId(id);
//    }
}
