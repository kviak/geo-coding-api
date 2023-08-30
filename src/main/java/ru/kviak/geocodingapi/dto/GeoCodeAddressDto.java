package ru.kviak.geocodingapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kviak.geocodingapi.dto.map_dto.AbstractGeoCodeDto;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GeoCodeAddressDto extends AbstractGeoCodeDto {
    private String address;
}
