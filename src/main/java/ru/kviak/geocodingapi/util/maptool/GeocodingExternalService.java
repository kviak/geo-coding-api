package ru.kviak.geocodingapi.util.maptool;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.kviak.geocodingapi.dto.GeoCodeAddressDto;
import ru.kviak.geocodingapi.dto.GeoCodeCoordinatesDto;
import ru.kviak.geocodingapi.dto.mapdto.AddressInfoDto;
import ru.kviak.geocodingapi.dto.mapdto.LocationSearchResultDto;
import ru.kviak.geocodingapi.dto.mapdto.PositionDto;
import ru.kviak.geocodingapi.util.error.GeoCodeAddressOrPositionNotFound;
import ru.kviak.geocodingapi.util.error.GeoCodeInvalidAddressOrPositionException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GeocodingExternalService {
    private final WebClient webClient;
    @Value("${tomtom.api.key}")
    private String key;
    public Optional<PositionDto> convert(GeoCodeAddressDto dto) {
        LocationSearchResultDto response = webClient.get()
                .uri("geocode/" + dto.getAddress() + ".json?key=" + key)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, error -> Mono.error(new GeoCodeInvalidAddressOrPositionException()))
                .bodyToMono(LocationSearchResultDto.class)
                .block();
        if (response.getResults().length==0) {
                throw new GeoCodeAddressOrPositionNotFound();
        }
        return Optional.ofNullable(response.getResults()[0].getPosition());
    }

    public Optional<String> convert(GeoCodeCoordinatesDto dto) {
        AddressInfoDto response = webClient.get()
                .uri("reverseGeocode/" + dto.getPosition().toString() + ".json?key=" + key)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, error -> Mono.error(new GeoCodeInvalidAddressOrPositionException()))
                .bodyToMono(AddressInfoDto.class)
                .block();
        if (response.getAddresses().isEmpty()) {
            throw new GeoCodeAddressOrPositionNotFound();
        }
        return Optional.ofNullable(response.getAddresses().get(0).getAddress().getFreeformAddress());
    }
}
