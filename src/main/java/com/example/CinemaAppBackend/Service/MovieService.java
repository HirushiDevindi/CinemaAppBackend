package com.example.CinemaAppBackend.Service;

import com.example.CinemaAppBackend.Dto.MovieDTO;
import com.example.CinemaAppBackend.Entity.Movie;
import com.example.CinemaAppBackend.Entity.MovieShow;
import com.example.CinemaAppBackend.Repo.MovieRepo;
import com.example.CinemaAppBackend.Repo.MovieShowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private  final MovieShowRepo movieShowRepo;

    public MovieService(MovieRepo movieRepo, MovieShowRepo movieShowRepo) {
        this.movieRepo = movieRepo;
        this.movieShowRepo = movieShowRepo;
    }

    public ResponseEntity<Map<String, Object>> addMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();

        String movieName = movieDTO.getName();
        String simpleMovieName = StringUtils.hasText(movieName) ? movieName.toLowerCase() : movieName;



        Movie existingMovie = movieRepo.findByName(simpleMovieName);

        if (existingMovie != null) {
            // A movie with the same name already exists, return a suitable message
            //throw new IllegalArgumentException("Movie with the same name already exists.");
            return ResponseEntity.ok(
                    new HashMap<String, Object>() {{
                        put("message", "Movie with the same name already exists.");
                        put("movie", movie);
                    }}
            );

        }



        //Movie movie = new Movie();

        movie.setName(simpleMovieName);
        movie.setLanguage(movieDTO.getLanguage());
        movie.setCountry(movieDTO.getCountry());
        movie.setMovieDuration(movieDTO.getMovieDuration());
        movie.setReleaseDate(movieDTO.getReleaseDate());


        movieRepo.save(movie);
        System.out.println("Movie is entered Successfully");
        //return movie;
        return ResponseEntity.ok(
                new HashMap<String, Object>() {{
                    put("message", "Movie is entered Successfully");
                    put("movie", movie);
                }}
        );

    }

    public ResponseEntity<Map<String, Object>> updateMovie(MovieDTO movieDTO, Long id) {
        Movie movie = new Movie();
        String movieName = movieDTO.getName();
        String simpleMovieName = StringUtils.hasText(movieName) ? movieName.toLowerCase() : movieName;

        Movie existingMovie = movieRepo.findByName(simpleMovieName);

        if (existingMovie != null && (existingMovie.getMovieId()!= id)) {
            // A movie with the same name already exists, but it is not the movie being updated
            //throw new IllegalArgumentException("Movie with the same name already exists.");
            return ResponseEntity.ok(
                    new HashMap<String, Object>() {{
                        put("message", "Movie with the same name already exists.");
                        put("movie", movie);
                    }}
            );
        }
        //Movie movie = new Movie();

        movie.setName(simpleMovieName);
        movie.setLanguage(movieDTO.getLanguage());
        movie.setCountry(movieDTO.getCountry());
        movie.setMovieDuration(movieDTO.getMovieDuration());
        movie.setReleaseDate(movieDTO.getReleaseDate());

        movie.setMovieId(id);
        movieRepo.save(movie);
        System.out.println("Movie is updated Successfully");
        return ResponseEntity.ok(
                new HashMap<String, Object>() {{
                    put("message", "Movie is updated Successfully");
                    put("movie", movie);
                }}
        );


    }

    public List<Movie> getMovies() {
        return movieRepo.findAll();
    }

    public Optional<Movie> getMovie(long id) {
        return movieRepo.findById(id);
    }

//    public String deleteMovie(Long id) {
//        movieRepo.deleteById(id);
//        return "Deleted " + id;
//    }

    //--
    public String deleteMovie(long id) {
        Optional<Movie> movieOptional = movieRepo.findById(id);

        if (movieOptional.isPresent()) {
            Movie movie = movieOptional.get();

            // Check if there are associated shows
            List<MovieShow> associatedMovieShows = movieShowRepo.findAllByMovieMovieId(movie.getMovieId());

            if (associatedMovieShows.isEmpty()) {
                // No associated shows found, safe to delete the movie
                movieRepo.deleteById(id);
                return id + " deleted";
            } else {
                // There are associated shows, return an appropriate message
                return "Cannot delete the movie show (ID: " + id + ") as it has associated movie shows.";
            }
        } else {
            // Movie show not found, return an appropriate message
            return "Movie  with ID " + id + " not found.";
        }
    }
    //--


}
