package br.com.piorfilmeapi.repository;

import br.com.piorfilmeapi.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByWinnerOrderByYear(String winner);
}
