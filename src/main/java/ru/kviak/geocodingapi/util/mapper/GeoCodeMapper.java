package ru.kviak.geocodingapi.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.kviak.geocodingapi.dto.GeoCodeResponseDto;
import ru.kviak.geocodingapi.model.GeoCodeEntity;


@Mapper
public interface GeoCodeMapper {
    GeoCodeMapper INSTANCE = Mappers.getMapper(GeoCodeMapper.class);

    default GeoCodeEntity convertCustom(GeoCodeResponseDto dto){
        GeoCodeEntity entity = new GeoCodeEntity();
        entity.setAddress(dto.getAddress());
        entity.setLatitude(dto.getPosition().getLat());
        entity.setLongitude(dto.getPosition().getLon());
        return entity;
    }

}
