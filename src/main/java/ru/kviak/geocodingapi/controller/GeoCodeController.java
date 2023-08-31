package ru.kviak.geocodingapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kviak.geocodingapi.dto.GeoCodeAddressDto;
import ru.kviak.geocodingapi.dto.GeoCodeCoordinatesDto;
import ru.kviak.geocodingapi.dto.GeoCodeResponseDto;
import ru.kviak.geocodingapi.service.GeoCodeAddressService;
import ru.kviak.geocodingapi.util.error.GeoCodeErrorResponse;
import ru.kviak.geocodingapi.util.error.GeoCodeInvalidAddressOrPositionException;

import java.io.IOException;
import java.time.Instant;
import java.util.Collections;


@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class GeoCodeController {
    private final GeoCodeAddressService geoCodeAddressService;

    @PostMapping("/geocode")
    public ResponseEntity<GeoCodeResponseDto> makeCoordinates(@RequestBody GeoCodeAddressDto dto) {
        return ResponseEntity.ok(geoCodeAddressService.convert(Collections.singletonList(dto)));
    }
    @PostMapping("/reverse-geocode")
    public ResponseEntity<GeoCodeResponseDto> makeAddress(@RequestBody GeoCodeCoordinatesDto dto) {
        return ResponseEntity.ok(geoCodeAddressService.convert(Collections.singletonList(dto)));
    }

    @ExceptionHandler
    private ResponseEntity<GeoCodeErrorResponse> handleException(GeoCodeInvalidAddressOrPositionException e){
        GeoCodeErrorResponse response = new GeoCodeErrorResponse(
                "Invalid data of position or address!",
                Instant.now()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    private ResponseEntity<GeoCodeErrorResponse> handleException(IOException e){
        GeoCodeErrorResponse response = new GeoCodeErrorResponse(
                "Cannot find this place!",
                Instant.now()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
