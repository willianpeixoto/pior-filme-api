package br.com.piorfilmeapi.controller.v1;

import br.com.piorfilmeapi.dto.MovieRequestDto;
import br.com.piorfilmeapi.dto.MovieResponseDto;
import br.com.piorfilmeapi.entity.Movie;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Api(tags = "Filmes")
@RequestMapping("/v1/movies")
public interface MovieApi {

    @ApiOperation("Lista todos os filmes")
    @GetMapping()
    ResponseEntity<List<MovieResponseDto>> getAllMovies();

    @ApiOperation("Busca um filme pelo ID")
    @GetMapping("/{id}")
    ResponseEntity<MovieResponseDto> getMovieById(@PathVariable Long id);

    @ApiOperation("Cria um novo filme")
    @PostMapping()
    ResponseEntity<MovieResponseDto> createMovie(@RequestBody MovieRequestDto movieRequest);

    @ApiOperation("Atualiza um filme existente")
    @PutMapping("/{id}")
    ResponseEntity<MovieResponseDto> updateMovie(@PathVariable Long id, @RequestBody MovieRequestDto movie);

    @ApiOperation("Exclui um filme pelo ID")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteMovie(@PathVariable Long id);
}

