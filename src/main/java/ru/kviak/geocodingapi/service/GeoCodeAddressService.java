package ru.kviak.geocodingapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kviak.geocodingapi.dto.GeoCodeAddressDto;
import ru.kviak.geocodingapi.dto.GeoCodeCoordinatesDto;
import ru.kviak.geocodingapi.dto.GeoCodeResponseDto;
import ru.kviak.geocodingapi.model.GeoCodeEntity;
import ru.kviak.geocodingapi.repository.GeoCodeRepository;
import ru.kviak.geocodingapi.util.map_tool.WorkWithTomTom;


@Service
@RequiredArgsConstructor
public class GeoCodeAddressService {
    private final WorkWithTomTom workWithTomTom;
    private final GeoCodeRepository geoCodeRepository;

    public GeoCodeResponseDto addressConvert(GeoCodeAddressDto dto) {
        GeoCodeResponseDto responseDto = new GeoCodeResponseDto(workWithTomTom.convert(dto.getAddress()), dto.getAddress());

        GeoCodeEntity entity = new GeoCodeEntity
                (null, responseDto.getPosition().getLat(), responseDto.getPosition().getLon(), responseDto.getAddress());
        geoCodeRepository.save(entity);
        return responseDto;
    }

    public GeoCodeResponseDto coordinateConvert(GeoCodeCoordinatesDto dto) {
        GeoCodeResponseDto responseDto = new GeoCodeResponseDto(dto.getPosition(), workWithTomTom.convert(dto.getPosition()));

        GeoCodeEntity entity = new GeoCodeEntity
                (null, responseDto.getPosition().getLat(), responseDto.getPosition().getLon(), responseDto.getAddress());
        geoCodeRepository.save(entity);
        return responseDto;
    }
}
