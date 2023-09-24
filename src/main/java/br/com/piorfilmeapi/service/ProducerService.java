package br.com.piorfilmeapi.service;

import br.com.piorfilmeapi.dto.MovieResponseDto;
import br.com.piorfilmeapi.dto.ProducerDto;
import br.com.piorfilmeapi.dto.ProducerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerService {

    private final MovieService movieService;

    public ProducerResponseDto getProducersWithAwardWinningMovies() {
        var movies = movieService.getWinningMovies();
        for(MovieResponseDto movie : movies) {
            //já temos os ganhadores, agora precisamos pegar apenas os produtores que aparecem mais de 1x
            //contabilizar o intervalo de tempo entre os prêmios de cada produtor (primeiro e o segundo prêmio, segundo e terceiro prêmio, etc)

            //RESPOSTA
            //(MIN) o que tiver o menor intervalo ganho 2 prêmios consecutivos é mais rápido
            //(MAX) o que tiver o maior intervalo
        }
        return null;
    }
}
