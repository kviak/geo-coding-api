package ru.kviak.geocodingapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kviak.geocodingapi.dto.GeoCodeAddressDto;
import ru.kviak.geocodingapi.dto.GeoCodeCoordinatesDto;
import ru.kviak.geocodingapi.dto.GeoCodeResponseDto;
import ru.kviak.geocodingapi.service.GeoCodeAddressService;

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
}
