package ru.kviak.geocodingapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kviak.geocodingapi.dto.GeoCodeAddressDto;
import ru.kviak.geocodingapi.dto.GeoCodeCoordinatesDto;
import ru.kviak.geocodingapi.dto.GeoCodeResponseDto;
import ru.kviak.geocodingapi.dto.map_dto.AbstractGeoCodeDto;
import ru.kviak.geocodingapi.model.GeoCodeEntity;
import ru.kviak.geocodingapi.repository.GeoCodeRepository;
import ru.kviak.geocodingapi.util.map_tool.WorkWithTomTom;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeoCodeAddressService {
    private final WorkWithTomTom workWithTomTom;
    private final GeoCodeRepository geoCodeRepository;

    public GeoCodeResponseDto convert(List<? extends AbstractGeoCodeDto> dto){
        GeoCodeResponseDto responseDto;
        if (dto.get(0).getClass() == GeoCodeCoordinatesDto.class){
            GeoCodeCoordinatesDto coordinatesDto  = (GeoCodeCoordinatesDto) dto.get(0);
            responseDto = new GeoCodeResponseDto(coordinatesDto.getPosition(), workWithTomTom.convert(coordinatesDto));
        }
        else {
            GeoCodeAddressDto addressDto  = (GeoCodeAddressDto) dto.get(0);
            responseDto = new GeoCodeResponseDto(workWithTomTom.convert(addressDto), addressDto.getAddress());
        }
        geoCodeRepository.save(new GeoCodeEntity(null,
                responseDto.getPosition().getLat(), responseDto.getPosition().getLon(), responseDto.getAddress()));
        return responseDto;
    }
}
