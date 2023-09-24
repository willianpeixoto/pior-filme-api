package br.com.piorfilmeapi.service;

import br.com.piorfilmeapi.dto.MovieResponseDto;
import br.com.piorfilmeapi.dto.ProducerDto;
import br.com.piorfilmeapi.dto.ProducerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProducerService {

    private final MovieService movieService;

    public ProducerResponseDto getProducersWithAwardWinningMovies() {
        var movies = movieService.getWinningMovies();

        var producerAwardsMap = getProducerWithYearsMap(movies);
        var producers = getProducerWithIntervals(producerAwardsMap);

        return getProducersWithAwardWinningMoviesResponse(producers);
    }

    private Map<String, List<Integer>> getProducerWithYearsMap(List<MovieResponseDto> movies) {
        Map<String, List<Integer>> producerAwardsMap = new HashMap<>();
        for(MovieResponseDto movie : movies) {
            String[] producerNames = movie.getProducers().split(",");
            for (String producerName : producerNames) {
                String trimmedProducerName = producerName.trim();

                producerAwardsMap.computeIfAbsent(trimmedProducerName, k -> new ArrayList<>()).add(movie.getYear());
            }
        }
        return producerAwardsMap;
    }

    private List<ProducerDto> getProducerWithIntervals(Map<String, List<Integer>> producerAwardsMap) {
        List<ProducerDto> producers = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> entry : producerAwardsMap.entrySet()) {
            if(entry.getValue().size() > 1) {
                List<Integer> years = entry.getValue();
                for (int i = 1; i < years.size(); i++) {
                    int interval = years.get(i) - years.get(i - 1);
                    var producer = getProducerDto(entry.getKey(), years, i, interval);
                    producers.add(producer);
                }
            }
        }
        return producers;
    }

    private ProducerDto getProducerDto(String producer, List<Integer> years, Integer i, Integer interval) {
        return ProducerDto.builder()
                .producer(producer)
                .interval(interval)
                .previousWin(years.get(i - 1))
                .followingWin(years.get(i))
                .build();
    }

    private ProducerResponseDto getProducersWithAwardWinningMoviesResponse(List<ProducerDto> producers) {
        var minAwards = Integer.MAX_VALUE;
        var maxAwards = Integer.MIN_VALUE;
        List<ProducerDto> minProducerDtos = new ArrayList<>();
        List<ProducerDto> maxProducerDtos = new ArrayList<>();
        for (ProducerDto producer : producers) {
            if(producer.getInterval() <= minAwards) {
                if(producer.getInterval() < minAwards) {
                    minProducerDtos = new ArrayList<>();
                }
                minProducerDtos.add(producer);
                minAwards = producer.getInterval();
            }
            if(producer.getInterval() >= maxAwards) {
                if(producer.getInterval() > maxAwards) {
                    maxProducerDtos = new ArrayList<>();
                }
                maxProducerDtos.add(producer);
                maxAwards = producer.getInterval();
            }
        }
        return ProducerResponseDto.builder()
                .min(minProducerDtos)
                .max(maxProducerDtos)
                .build();
    }
}
