package ru.kviak.geocodingapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kviak.geocodingapi.dto.map_dto.PositionDto;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeoCodeCoordinatesDto {
    private PositionDto position;
}
