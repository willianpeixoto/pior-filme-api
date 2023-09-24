package br.com.piorfilmeapi.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProducerResponseDto {
    private List<ProducerDto> min;
    private List<ProducerDto> max;
}
