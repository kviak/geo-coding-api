package ru.kviak.geocodingapi.dto.map_dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PositionDto {
    private BigDecimal lat;
    private BigDecimal lon;
}
