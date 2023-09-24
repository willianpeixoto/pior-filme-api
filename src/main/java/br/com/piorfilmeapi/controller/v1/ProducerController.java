package br.com.piorfilmeapi.controller.v1;

import br.com.piorfilmeapi.dto.ProducerResponseDto;
import br.com.piorfilmeapi.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController implements ProducerApi {

    private final ProducerService producerService;

    @Override
    public ResponseEntity<ProducerResponseDto> getProducersWithAwardWinningMovies() {
        ProducerResponseDto producer = producerService.getProducersWithAwardWinningMovies();
        return null;
    }
}
