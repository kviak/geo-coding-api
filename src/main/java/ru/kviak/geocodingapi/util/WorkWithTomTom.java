package ru.kviak.geocodingapi.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class WorkWithTomTom {
    @Value("${tomtom.api.key}")
    private String key;
    @Value("${tomtom.api.url}")
    private String uri;

    @SneakyThrows
    public Position convert(String address) {

        URL url = new URL(new StringBuffer(uri)
                .append(address.replace(' ', '%'))
                .append(".json?key=")
                .append(key).toString());
        System.out.println(url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        System.out.println(connection.getResponseCode() + " " + connection.getResponseMessage());


        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        LocationSearchResult locationSearchResult = objectMapper.readValue(content.toString(), LocationSearchResult.class);
        System.out.println(locationSearchResult.getResults()[0].getPosition().getLat() + ", " +locationSearchResult.getResults()[0].getPosition().getLon());
        return locationSearchResult.getResults()[0].getPosition();
    }
}
