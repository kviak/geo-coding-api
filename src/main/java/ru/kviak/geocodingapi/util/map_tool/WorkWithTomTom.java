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

import java.net.HttpURLConnection;

@Component
@RequiredArgsConstructor
public class WorkWithTomTom {
    private final ConnectionMaker connectionMaker;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public PositionDto convert(GeoCodeAddressDto dto) {
        HttpURLConnection connection = connectionMaker.make(dto.getAddress());
        LocationSearchResultDto locationSearchResult = objectMapper.readValue(connectionMaker.readResponse(connection), LocationSearchResultDto.class);
        return locationSearchResult.getResults()[0].getPosition();
    }

    @SneakyThrows
    public String convert(GeoCodeCoordinatesDto dto) {
        HttpURLConnection connection = connectionMaker.make(dto.getPosition());
        AddressInfoDto locationSearchResult = objectMapper.readValue(connectionMaker.readResponse(connection), AddressInfoDto.class);
        return locationSearchResult.getAddresses().get(0).getAddress().getFreeformAddress();
    }
}
