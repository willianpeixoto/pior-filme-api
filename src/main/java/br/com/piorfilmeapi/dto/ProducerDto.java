package br.com.piorfilmeapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProducerDto {
    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;
}
