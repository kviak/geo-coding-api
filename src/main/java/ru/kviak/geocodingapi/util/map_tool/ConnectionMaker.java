package ru.kviak.geocodingapi.util.map_tool;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.kviak.geocodingapi.dto.map_dto.PositionDto;

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
    public HttpURLConnection make(String address){
        URL url = new URL(new StringBuffer(uri)
                .append(address.replace(' ', '%'))
                .append(".json?key=")
                .append(key).toString());
        System.out.println(url);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        System.out.println(connection.getResponseCode() + " " + connection.getResponseMessage());
        return connection;
    }

    @SneakyThrows
    public HttpURLConnection make(PositionDto position){
        URL url = new URL(new StringBuffer("https://api.tomtom.com/search/2/reverseGeocode/")
                .append(position.toString())
                .append(".json?key=")
                .append(key)
                .append("&radius=100").toString());

        System.out.println(url);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        System.out.println(connection.getResponseCode() + " " + connection.getResponseMessage());
        return connection;
    }

}
