package ru.kviak.geocodingapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kviak.geocodingapi.dto.GeoCodeAddressDto;
import ru.kviak.geocodingapi.dto.GeoCodeCoordinatesDto;
import ru.kviak.geocodingapi.dto.GeoCodeResponseDto;
import ru.kviak.geocodingapi.util.map_tool.WorkWithTomTom;


@Service
@RequiredArgsConstructor
public class GeoCodeAddressService {
    private final WorkWithTomTom workWithTomTom;

    public GeoCodeResponseDto addressConvert(GeoCodeAddressDto dto) {
        return new GeoCodeResponseDto(workWithTomTom.convert(dto.getAddress()), dto.getAddress());
    }

    public GeoCodeResponseDto coordinateConvert(GeoCodeCoordinatesDto dto) {
        System.out.println(dto.getPosition());
        return new GeoCodeResponseDto(dto.getPosition(), workWithTomTom.convert(dto.getPosition()));
    }
}
