package ru.kviak.geocodingapi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.kviak.geocodingapi.dto.GeoCodeAddressDto;
import ru.kviak.geocodingapi.dto.GeoCodeCoordinatesDto;
import ru.kviak.geocodingapi.dto.GeoCodeResponseDto;
import ru.kviak.geocodingapi.dto.mapdto.PositionDto;
import ru.kviak.geocodingapi.repository.GeoCodeRepository;
import ru.kviak.geocodingapi.util.error.GeoCodeInvalidAddressOrPositionException;
import ru.kviak.geocodingapi.util.maptool.GeocodingExternalService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GeoCodeAddressServiceTest {
    @Mock
    private GeocodingExternalService geocodingExternalService;
    @Mock
    private GeoCodeRepository geoCodeRepository;
    private GeoCodeAddressService geoCodeAddressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        geoCodeAddressService = new GeoCodeAddressService(geocodingExternalService, geoCodeRepository);
    }

    @Test
    void testConvertWithAddressShouldReturnResponseDtoAndSaveInDB(){
        GeoCodeAddressDto geoCodeAddressDto = new GeoCodeAddressDto("Kirov");
        List<GeoCodeAddressDto> list = List.of(geoCodeAddressDto);
        GeoCodeResponseDto responseDto = new GeoCodeResponseDto(new PositionDto(BigDecimal.TEN, BigDecimal.TEN), "Kirov");

        when(geocodingExternalService.convert(geoCodeAddressDto))
                .thenReturn(Optional.of(new PositionDto(BigDecimal.TEN, BigDecimal.TEN)));
        GeoCodeResponseDto expected = geoCodeAddressService.convert(list);

        assertThat(responseDto)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void testConvertWithCoordinatesShouldReturnResponseDtoAndSaveInDB(){
        GeoCodeCoordinatesDto geoCodeCoordinatesDto = new GeoCodeCoordinatesDto(new PositionDto(BigDecimal.TEN, BigDecimal.TEN));
        List<GeoCodeCoordinatesDto> list = List.of(geoCodeCoordinatesDto);
        GeoCodeResponseDto responseDto = new GeoCodeResponseDto(geoCodeCoordinatesDto.getPosition(), "Kirov");

        when(geocodingExternalService.convert(geoCodeCoordinatesDto))
                .thenReturn(Optional.of("Kirov"));
        GeoCodeResponseDto expected = geoCodeAddressService.convert(list);

        assertThat(responseDto)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void testExpectedExceptionFindOrder(){
        GeoCodeCoordinatesDto geoCodeCoordinatesDto = new GeoCodeCoordinatesDto(new PositionDto(BigDecimal.TEN, BigDecimal.TEN));
        List<GeoCodeCoordinatesDto> list = List.of(geoCodeCoordinatesDto);

        when(geocodingExternalService.convert(geoCodeCoordinatesDto))
                .thenReturn(Optional.empty());


        GeoCodeInvalidAddressOrPositionException thrown = Assertions.assertThrows(GeoCodeInvalidAddressOrPositionException.class, () ->
            geoCodeAddressService.convert(list));
        Assertions.assertEquals(GeoCodeInvalidAddressOrPositionException.class ,thrown.getClass());
    }

}
