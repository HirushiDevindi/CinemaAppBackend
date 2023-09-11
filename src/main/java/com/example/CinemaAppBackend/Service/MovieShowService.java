package com.example.CinemaAppBackend.Service;

import com.example.CinemaAppBackend.Dto.MovieShowDTO;
import com.example.CinemaAppBackend.Entity.Movie;
import com.example.CinemaAppBackend.Entity.MovieShow;
import com.example.CinemaAppBackend.Entity.Seat;
import com.example.CinemaAppBackend.Repo.MovieRepo;
import com.example.CinemaAppBackend.Repo.MovieShowRepo;
import com.example.CinemaAppBackend.Repo.SeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class MovieShowService {

    private  final MovieRepo movieRepo;

    private  final MovieShowRepo movieShowRepo;

    @Autowired
    private  final SeatRepo seatRepo;

    @Autowired
    public MovieShowService(MovieRepo movieRepo, MovieShowRepo movieShowRepo, SeatRepo seatRepo) {
        this.movieRepo = movieRepo;
        this.movieShowRepo = movieShowRepo;
        this.seatRepo = seatRepo;
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mma");



    public ResponseEntity<Map<String, Object>> addShow(MovieShowDTO movieShowDTO) {
        MovieShow temp= new MovieShow();

        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime showDateTime = LocalDateTime.of(movieShowDTO.getDate(), LocalTime.parse(movieShowDTO.getStartTime(), formatter));

        if (showDateTime.isBefore(currentDateTime)) {
            return ResponseEntity.ok(
                    new HashMap<String, Object>() {{
                        put("message", "Cannot add a movie show with a past date and time.");
                        put("movieShow", null);
                    }}
            );
        }



        MovieShow existingShow = movieShowRepo.findByDateAndStartTimeAndEndTime(
                movieShowDTO.getDate(), movieShowDTO.getStartTime(), movieShowDTO.getEndTime());

        if (existingShow != null) {
            // A movie show with the same date, start time, and end time already exists
            return ResponseEntity.ok(
                    new HashMap<String, Object>() {{
                        put("message", "Date and time slots are already assigned");
                        put("movieShow", temp);
                    }}
            );
        }

        temp.setDate(movieShowDTO.getDate());
        temp.setEndTime(movieShowDTO.getEndTime());
        temp.setStartTime(movieShowDTO.getStartTime());

        Optional movieS= movieRepo.findById(movieShowDTO.getMovieId());
        if (movieS.isPresent()) {
            Movie movieEntity = (Movie) movieS.get();
            temp.setMovie(movieEntity);
        }
        movieShowRepo.save(temp);
        return ResponseEntity.ok(
                new HashMap<String, Object>() {{
                    put("message", "Movie show is entered Successfully");
                    put("movieShow", temp);
                }}
        );
    }

    public  ResponseEntity<Map<String, Object>> editShow(MovieShowDTO movieShowDTO, Long id) {

        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime showDateTime = LocalDateTime.of(movieShowDTO.getDate(), LocalTime.parse(movieShowDTO.getStartTime(), formatter));

        if (showDateTime.isBefore(currentDateTime)) {
            return ResponseEntity.ok(
                    new HashMap<String, Object>() {{
                        put("message", "Cannot edit a movie show with a past date and time.");
                        put("movieShow", null);
                    }}
            );
        }


        MovieShow existingShow = movieShowRepo.findByDateAndStartTimeAndEndTime(
                movieShowDTO.getDate(), movieShowDTO.getStartTime(), movieShowDTO.getEndTime());

        if (existingShow != null && (existingShow.getMovieShowId()!= id)) {
            // A movie show with the same date, start time, and end time already exists
            return ResponseEntity.ok(
                    new HashMap<String, Object>() {{
                        put("message", "Date and time slots are already assigned");
                        put("movieShow", null);
                    }}
            );
        }
        MovieShow temp = new MovieShow();

        Optional movieShowOptional= movieShowRepo.findById(id);
        if (movieShowOptional.isPresent()) {
            temp = (MovieShow) movieShowOptional.get();
        }
        if(movieShowDTO.getDate() !=null){temp.setDate(movieShowDTO.getDate());}
        if(movieShowDTO.getStartTime() !=null){temp.setStartTime(movieShowDTO.getStartTime());}
        if(movieShowDTO.getEndTime() !=null){temp.setEndTime(movieShowDTO.getEndTime());}

        if(movieShowDTO.getMovieId()!=0) {
            Optional movieS= movieRepo.findById(movieShowDTO.getMovieId());
            if (movieS.isPresent()) {
                Movie movieEntity = (Movie) movieS.get();
                temp.setMovie(movieEntity);
            }
        }

        temp.setMovieShowId(id);
        movieShowRepo.save(temp);
        MovieShow finalTemp = temp;
        return ResponseEntity.ok(
                new HashMap<String, Object>() {{
                    put("message", "Movie show is updated Successfully");
                    put("movieShow", finalTemp);
                }}
        );

    }

//    public  String deleteShow(long id) {
//        movieShowRepo.deleteById(id);
//        return id + " deleted";
//    }

    //---
    public String deleteShow(long id) {
        Optional<MovieShow> movieShowOptional = movieShowRepo.findById(id);

        if (movieShowOptional.isPresent()) {
            MovieShow movieShow = movieShowOptional.get();

            // Check if there are associated seats
            List<Seat> associatedSeats = seatRepo.findAllByMovieShowMovieShowId(movieShow.getMovieShowId());

            if (associatedSeats.isEmpty()) {
                // No associated seats found, safe to delete the movie show
                movieShowRepo.deleteById(id);
                return id + " deleted";
            } else {
                // There are associated seats, return an appropriate message
                return "Cannot delete the movie show (ID: " + id + ") as it has associated seats.";
            }
        } else {
            // Movie show not found, return an appropriate message
            return "Movie show with ID " + id + " not found.";
        }
    }
    //---

    public List<MovieShow> getAllShowTerms() {
        return movieShowRepo.findAll();
    }


    public  List<MovieShow> findShowsByMovieId(Long id) {
        return movieShowRepo.findAllByMovieMovieId(id);

    }

    public Optional<MovieShow> getMovieShow(long id) {
        return movieShowRepo.findById(id);
    }
}
