package ru.kviak.geocodingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kviak.geocodingapi.model.GeoCodeEntity;

import java.util.UUID;

public  interface GeoCodeRepository extends JpaRepository<GeoCodeEntity, UUID> {}
