package ru.kviak.geocodingapi.util.map_tool;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.kviak.geocodingapi.dto.map_dto.PositionDto;
import ru.kviak.geocodingapi.util.error.GeoCodeInvalidAddressOrPositionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class ConnectionMaker {
    @Value("${tomtom.api.key}")
    private String key;
    @Value("${tomtom.api.url}")
    private String uri;
    private HttpURLConnection connection;

    @SneakyThrows
    public HttpURLConnection makeGeoCode(String address){
        URL url = new URL(new StringBuffer(uri)
                .append(address.replace(' ', '%'))
                .append(".json?key=")
                .append(key).toString());
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return connection;
    }


    public String readResponse(HttpURLConnection connection) throws IOException {
        if (connection.getResponseCode() == 400) {throw new GeoCodeInvalidAddressOrPositionException();}
        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        connection.disconnect();
        return content.toString();
    }

    @SneakyThrows
    public HttpURLConnection makeReverseGeoCode(PositionDto position){
        URL url = new URL(new StringBuffer("https://api.tomtom.com/search/2/reverseGeocode/")
                .append(position.toString())
                .append(".json?key=")
                .append(key)
                .append("&radius=100").toString());

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return connection;
    }

}
