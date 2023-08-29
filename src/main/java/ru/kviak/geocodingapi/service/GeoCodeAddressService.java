package ru.kviak.geocodingapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kviak.geocodingapi.dto.GeoCodeAddressDto;
import ru.kviak.geocodingapi.dto.GeoCodeResponseDto;
import ru.kviak.geocodingapi.util.WorkWithTomTom;


@Service
@RequiredArgsConstructor
public class GeoCodeAddressService {
    private final WorkWithTomTom workWithTomTom;

    public GeoCodeResponseDto convert(GeoCodeAddressDto dto) {
        return new GeoCodeResponseDto(workWithTomTom.convert(dto.getAddress()), dto.getAddress());
    }
}
