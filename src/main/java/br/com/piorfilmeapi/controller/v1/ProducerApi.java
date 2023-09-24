package br.com.piorfilmeapi.controller.v1;

import br.com.piorfilmeapi.dto.ProducerResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "Produtores")
@RequestMapping("/v1/producers")
public interface ProducerApi {

    @ApiOperation("Lista os produtores com filmes premiados mais rápido e com maior e menor intervalo entre prêmios")
    @GetMapping()
    ResponseEntity<ProducerResponseDto> getProducersWithAwardWinningMovies();
}
