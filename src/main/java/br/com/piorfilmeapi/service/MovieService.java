package br.com.piorfilmeapi.service;

import br.com.piorfilmeapi.entity.Movie;
import br.com.piorfilmeapi.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Optional<Movie> updateMovie(Long id, Movie movie) {
        if (!movieRepository.existsById(id)) {
            return Optional.empty();
        }

        movie.setId(id);
        return Optional.of(movieRepository.save(movie));
    }

    public boolean deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            return false;
        }

        movieRepository.deleteById(id);
        return true;
    }
}


