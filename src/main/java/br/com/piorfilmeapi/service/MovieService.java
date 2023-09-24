package br.com.piorfilmeapi.service;

import br.com.piorfilmeapi.dto.MovieRequestDto;
import br.com.piorfilmeapi.dto.MovieResponseDto;
import br.com.piorfilmeapi.mapper.MovieMapper;
import br.com.piorfilmeapi.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public List<MovieResponseDto> getAllMovies() {
        var movies = movieRepository.findAll();
        return movieMapper.moviesToMovieResponseDtos(movies);
    }

    public Optional<MovieResponseDto> getMovieById(Long id) {
        var movie = movieRepository.findById(id);
        if(movie.isEmpty()) {
            return Optional.empty();
        }
        var movieResponse = movieMapper.movieToMovieResponseDto(movie.get());
        return Optional.of(movieResponse);
    }

    public List<MovieResponseDto> getWinningMovies() {
        var movies = movieRepository.findByWinnerOrderByYear("yes");
        return movieMapper.moviesToMovieResponseDtos(movies);
    }

    public MovieResponseDto createMovie(MovieRequestDto movieRequest) {
        var movie = movieMapper.movieRequestDtoToMovie(movieRequest);
        var createdMovie = movieRepository.save(movie);
        return movieMapper.movieToMovieResponseDto(createdMovie);
    }

    public Optional<MovieResponseDto> updateMovie(Long id, MovieRequestDto movieRequest) {
        if (!movieRepository.existsById(id)) {
            return Optional.empty();
        }

        var movie = movieMapper.movieRequestDtoToMovie(movieRequest);
        movie.setId(id);
        var updatedMovie = movieRepository.save(movie);
        return Optional.of(movieMapper.movieToMovieResponseDto(updatedMovie));
    }

    public boolean deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            return false;
        }

        movieRepository.deleteById(id);
        return true;
    }
}


