package ru.kviak.geocodingapi.util.map_tool;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.kviak.geocodingapi.dto.GeoCodeAddressDto;
import ru.kviak.geocodingapi.dto.GeoCodeCoordinatesDto;
import ru.kviak.geocodingapi.dto.map_dto.AddressInfoDto;
import ru.kviak.geocodingapi.dto.map_dto.LocationSearchResultDto;
import ru.kviak.geocodingapi.dto.map_dto.PositionDto;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GeocodingExternalService {
    private final ConnectionMaker connectionMaker;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public Optional<PositionDto> convert(GeoCodeAddressDto dto) {
        HttpURLConnection connection = connectionMaker.makeGeoCode(dto.getAddress());
        LocationSearchResultDto locationSearchResult = objectMapper.readValue(connectionMaker.readResponse(connection), LocationSearchResultDto.class);
        if (locationSearchResult.getResults().length==0) {
            throw new IOException();
        }
        return Optional.ofNullable(locationSearchResult.getResults()[0].getPosition());
    }

    @SneakyThrows
    public Optional<String> convert(GeoCodeCoordinatesDto dto) {
        HttpURLConnection connection = connectionMaker.makeReverseGeoCode(dto.getPosition());
        AddressInfoDto locationSearchResult = objectMapper.readValue(connectionMaker.readResponse(connection), AddressInfoDto.class);
        if (locationSearchResult.getAddresses().isEmpty()) {
            throw new IOException();
        }
        return Optional.ofNullable(locationSearchResult.getAddresses().get(0).getAddress().getFreeformAddress());
    }
}
