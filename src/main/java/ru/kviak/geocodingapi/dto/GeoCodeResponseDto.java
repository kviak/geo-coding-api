package ru.kviak.geocodingapi.dto;

import lombok.*;
import ru.kviak.geocodingapi.dto.mapdto.PositionDto;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GeoCodeResponseDto {
    private PositionDto position;
    private String address;
}
