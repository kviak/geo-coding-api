package ru.kviak.geocodingapi.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import ru.kviak.geocodingapi.dto.GeoCodeAddressDto;
import ru.kviak.geocodingapi.dto.GeoCodeCoordinatesDto;
import ru.kviak.geocodingapi.dto.GeoCodeResponseDto;
import ru.kviak.geocodingapi.service.GeoCodeAddressService;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

public class GeoCodeControllerTest {

    @Mock
    private GeoCodeAddressService geoCodeAddressService;
    private GeoCodeController geoCodeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        geoCodeController = new GeoCodeController(geoCodeAddressService);
    }

    @Test
    void makeCoordinatesShouldReturnResponseEntity(){
        GeoCodeAddressDto dto = new GeoCodeAddressDto();
        GeoCodeResponseDto responseDto = new GeoCodeResponseDto();

        when(geoCodeAddressService.convert(Collections.singletonList(dto)))
                .thenReturn(responseDto);

        assertThat(ResponseEntity.ok(responseDto))
                .usingRecursiveComparison()
                .isEqualTo(geoCodeController.makeCoordinates(dto));
    }

    @Test
    void makeAddressShouldReturnResponseEntity(){
        GeoCodeCoordinatesDto dto = new GeoCodeCoordinatesDto();
        GeoCodeResponseDto responseDto = new GeoCodeResponseDto();

        when(geoCodeAddressService.convert(Collections.singletonList(dto)))
                .thenReturn(responseDto);

        assertThat(ResponseEntity.ok(responseDto))
                .usingRecursiveComparison()
                .isEqualTo(geoCodeController.makeAddress(dto));
    }
}
