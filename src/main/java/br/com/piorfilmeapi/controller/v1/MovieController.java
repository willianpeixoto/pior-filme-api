package br.com.piorfilmeapi.controller.v1;

import br.com.piorfilmeapi.entity.Movie;
import br.com.piorfilmeapi.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MovieController implements MovieApi {

    private final MovieService service;

    @Override
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = service.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Movie> getMovieById(Long id) {
        Optional<Movie> movie = service.getMovieById(id);
        if (movie.isPresent()) {
            return ResponseEntity.ok(movie.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Movie> createMovie(Movie movie) {
        Movie createdMovie = service.createMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
    }

    @Override
    public ResponseEntity<Movie> updateMovie(Long id, Movie movie) {
        Optional<Movie> updatedMovie = service.updateMovie(id, movie);
        if (updatedMovie.isPresent()) {
            return ResponseEntity.ok(updatedMovie.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteMovie(Long id) {
        boolean deleted = service.deleteMovie(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
