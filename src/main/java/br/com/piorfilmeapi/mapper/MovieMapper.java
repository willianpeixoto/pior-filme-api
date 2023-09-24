package br.com.piorfilmeapi.mapper;

import br.com.piorfilmeapi.dto.MovieRequestDto;
import br.com.piorfilmeapi.dto.MovieResponseDto;
import br.com.piorfilmeapi.entity.Movie;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    Movie movieRequestDtoToMovie(MovieRequestDto movieRequestDto);
    MovieResponseDto movieToMovieResponseDto(Movie movie);
    List<MovieResponseDto> moviesToMovieResponseDtos(List<Movie> movies);
}