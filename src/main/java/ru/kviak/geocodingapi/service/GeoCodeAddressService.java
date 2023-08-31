package ru.kviak.geocodingapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kviak.geocodingapi.dto.GeoCodeAddressDto;
import ru.kviak.geocodingapi.dto.GeoCodeCoordinatesDto;
import ru.kviak.geocodingapi.dto.GeoCodeResponseDto;
import ru.kviak.geocodingapi.dto.map_dto.AbstractGeoCodeDto;
import ru.kviak.geocodingapi.repository.GeoCodeRepository;
import ru.kviak.geocodingapi.util.error.GeoCodeInvalidAddressOrPositionException;
import ru.kviak.geocodingapi.util.map_tool.GeocodingExternalService;
import ru.kviak.geocodingapi.util.mapper.GeoCodeMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeoCodeAddressService {
    private final GeocodingExternalService geocodingExternalService;
    private final GeoCodeRepository geoCodeRepository;

    public GeoCodeResponseDto convert(List<? extends AbstractGeoCodeDto> dto){
        GeoCodeResponseDto responseDto;
        if (dto.get(0).getClass() == GeoCodeCoordinatesDto.class){
            GeoCodeCoordinatesDto coordinatesDto  = (GeoCodeCoordinatesDto) dto.get(0);
            responseDto = new GeoCodeResponseDto(coordinatesDto.getPosition(), geocodingExternalService.convert(coordinatesDto).orElseThrow(GeoCodeInvalidAddressOrPositionException::new));
        }
        else {
            GeoCodeAddressDto addressDto  = (GeoCodeAddressDto) dto.get(0);
            responseDto = new GeoCodeResponseDto(geocodingExternalService.convert(addressDto).orElseThrow(GeoCodeInvalidAddressOrPositionException::new), addressDto.getAddress());
        }
        System.out.println(responseDto.getAddress());
        geoCodeRepository.save(GeoCodeMapper.INSTANCE.convertCustom(responseDto));
        return responseDto;
    }
}
