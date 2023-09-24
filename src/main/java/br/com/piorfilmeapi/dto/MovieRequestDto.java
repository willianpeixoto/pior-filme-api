package br.com.piorfilmeapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieRequestDto {
    private int year;
    private String title;
    private String studios;
    private String producers;
    private String winner;
}
