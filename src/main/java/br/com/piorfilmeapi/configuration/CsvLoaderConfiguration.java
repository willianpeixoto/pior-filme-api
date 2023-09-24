package br.com.piorfilmeapi.configuration;

import br.com.piorfilmeapi.entity.Movie;
import br.com.piorfilmeapi.repository.MovieRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class CsvLoaderConfiguration {

    private final ResourceLoader resourceLoader;
    private final MovieRepository movieRepository;
    @Value("${movies.csv.file}")
    private String moviesCsvFile;

    @PostConstruct
    public void loadCsvData() {
        String a = moviesCsvFile;
        try {
            Resource resource = resourceLoader.getResource(moviesCsvFile);
            Reader reader = new FileReader(resource.getFile());
            CsvToBean<Movie> csvToBean = new CsvToBeanBuilder<Movie>(reader)
                    .withType(Movie.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(';')
                    .withThrowExceptions(false)
                    .build();

            List<Movie> movies = csvToBean.parse();
            var normalizedMovies = normalizeMovies(movies);
            movieRepository.saveAll(normalizedMovies);

            log.info("Foram importados {} fimes com sucesso!", movies.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Movie> normalizeMovies(List<Movie> movies) {
        for(Movie movie : movies) {
            if(movie.getProducers().contains(" and ")) {
                var producers = movie.getProducers();
                var normalizedProducer = producers.replace(", and ", ",");
                movie.setProducers(normalizedProducer.replace(" and ", ","));
            }
            if(movie.getWinner().equalsIgnoreCase("yes")) {
                movie.setWinner("yes");
            }
        }
        return movies;
    }
}
