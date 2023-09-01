package ru.kviak.geocodingapi.dto.map_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PositionDto {
    private BigDecimal lat;
    private BigDecimal lon;

    @Override
    public String toString() {
        return   lat + "," + lon;
    }
}
