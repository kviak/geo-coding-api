package ru.kviak.geocodingapi.util.map_tool;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.kviak.geocodingapi.dto.map_dto.AddressInfoDto;
import ru.kviak.geocodingapi.dto.map_dto.LocationSearchResultDto;
import ru.kviak.geocodingapi.dto.map_dto.PositionDto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

@Component
@RequiredArgsConstructor
public class WorkWithTomTom {
    private final ConnectionMaker connectionMaker;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public PositionDto convert(String address) {

        HttpURLConnection connection = connectionMaker.make(address);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        connection.disconnect();
        in.close();

        LocationSearchResultDto locationSearchResult = objectMapper.readValue(content.toString(), LocationSearchResultDto.class);
        System.out.println(locationSearchResult.getResults()[0].getPosition().getLat() + ", " +locationSearchResult.getResults()[0].getPosition().getLon());
        return locationSearchResult.getResults()[0].getPosition();
    }

    @SneakyThrows
    public String convert(PositionDto position) {

        HttpURLConnection connection = connectionMaker.make(position);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        connection.disconnect();
        in.close();
        System.out.println(content.toString());
        AddressInfoDto locationSearchResult = objectMapper.readValue(content.toString(), AddressInfoDto.class);
        System.out.println(locationSearchResult.getAddresses().get(0));

        return locationSearchResult.getAddresses().get(0).getAddress().getFreeformAddress();
    }
}
