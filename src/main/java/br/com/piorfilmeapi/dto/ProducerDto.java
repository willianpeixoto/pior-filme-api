package br.com.piorfilmeapi.dto;

import lombok.Builder;

@Builder
public class ProducerDto {
    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;
}
