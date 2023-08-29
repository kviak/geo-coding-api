package ru.kviak.geocodingapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kviak.geocodingapi.dto.map_dto.PositionDto;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GeoCodeResponseDto {
    private PositionDto position;
    private String address;
}