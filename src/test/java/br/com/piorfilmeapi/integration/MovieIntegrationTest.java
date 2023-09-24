package br.com.piorfilmeapi.integration;

import br.com.piorfilmeapi.dto.MovieRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class MovieIntegrationTest {

    private static final String URI = "/v1/movies";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllMovies() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetMovieById() throws Exception {
        Long movieId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.get(URI.concat("/{id}"), movieId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCreateMovie() throws Exception {
        var movie = MovieRequestDto.builder().build();
        var jsonRequest = objectMapper.writeValueAsString(movie);
        mockMvc.perform(MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateMovie() throws Exception {
        var movieId = 2L;
        var updatedMovie = MovieRequestDto.builder().build();
        var jsonRequest = objectMapper.writeValueAsString(updatedMovie);
        mockMvc.perform(MockMvcRequestBuilders.put(URI.concat("/{id}"), movieId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testDeleteMovie() throws Exception {
        var movieId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.delete(URI.concat("/{id}"), movieId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}

