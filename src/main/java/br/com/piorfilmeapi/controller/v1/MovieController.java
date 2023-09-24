package br.com.piorfilmeapi.controller.v1;

import br.com.piorfilmeapi.dto.MovieRequestDto;
import br.com.piorfilmeapi.dto.MovieResponseDto;
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

    private final MovieService movieService;

    @Override
    public ResponseEntity<List<MovieResponseDto>> getAllMovies() {
        List<MovieResponseDto> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MovieResponseDto> getMovieById(Long id) {
        Optional<MovieResponseDto> movie = movieService.getMovieById(id);
        if (movie.isPresent()) {
            return ResponseEntity.ok(movie.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<MovieResponseDto> createMovie(MovieRequestDto movieRequest) {
        var createdMovie = movieService.createMovie(movieRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
    }

    @Override
    public ResponseEntity<MovieResponseDto> updateMovie(Long id, MovieRequestDto movieRequest) {
        Optional<MovieResponseDto> updatedMovie = movieService.updateMovie(id, movieRequest);
        if (updatedMovie.isPresent()) {
            return ResponseEntity.ok(updatedMovie.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteMovie(Long id) {
        boolean deleted = movieService.deleteMovie(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
