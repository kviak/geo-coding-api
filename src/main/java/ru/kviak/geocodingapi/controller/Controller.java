package ru.kviak.geocodingapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kviak.geocodingapi.dto.GeoCodeAddressDto;
import ru.kviak.geocodingapi.dto.GeoCodeResponseDto;
import ru.kviak.geocodingapi.service.GeoCodeAddressService;


@RestController
@RequestMapping("/api/v1/geocode")
@RequiredArgsConstructor
public class Controller {
    private final GeoCodeAddressService geoCodeAddressService;

    @GetMapping
    public ResponseEntity<GeoCodeResponseDto> handle(@RequestBody GeoCodeAddressDto dto){
        System.out.println("GOOOIDA");
        return ResponseEntity.ok(geoCodeAddressService.convert(dto));
    }
}
