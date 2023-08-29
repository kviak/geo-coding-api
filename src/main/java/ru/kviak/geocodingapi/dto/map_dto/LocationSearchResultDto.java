package ru.kviak.geocodingapi.dto.map_dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class LocationSearchResultDto {
    private Summary summary;
    private Result[] results;
    private Address[] addresses;
    private PositionDto position;

    // Геттеры и сеттеры (необязательно, но рекомендуется)
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class Summary {
        private String query;
        private String queryType;
        private int queryTime;
        private int numResults;
        private int offset;
        private int totalResults;
        private int fuzzyLevel;

        // Геттеры и сеттеры (необязательно, но рекомендуется)
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class Result {
        private String type;
        private String id;
        private double score;
        private MatchConfidence matchConfidence;
        private Address address;
        private PositionDto position;
        private Viewport viewport;
        private BoundingBox boundingBox;
        private DataSources dataSources;
        private String entityType;

        // Геттеры и сеттеры (необязательно, но рекомендуется)
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class Address {
        private String streetName;
        private String municipality;
        private String countrySecondarySubdivision;
        private String countryTertiarySubdivision;
        private String countrySubdivision;
        private String postalCode;
        private String countryCode;
        private String country;
        private String countryCodeISO3;
        private String freeformAddress;
        private String localName;
        private String municipalitySubdivision;

        // Геттеры и сеттеры (необязательно, но рекомендуется)
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class MatchConfidence {
        private double score;

        // Геттеры и сеттеры (необязательно, но рекомендуется)
    }


    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class Viewport {
        private Point topLeftPoint;
        private Point btmRightPoint;

        // Геттеры и сеттеры (необязательно, но рекомендуется)
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class Point {
        private double lat;
        private double lon;

        // Геттеры и сеттеры (необязательно, но рекомендуется)
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class BoundingBox {
        private Point topLeftPoint;
        private Point btmRightPoint;

        // Геттеры и сеттеры (необязательно, но рекомендуется)
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class DataSources {
        @JsonProperty("geometry")
        private Geometry geometry;

        // Геттеры и сеттеры (необязательно, но рекомендуется)
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class Geometry {
        private String id;

        // Геттеры и сеттеры (необязательно, но рекомендуется)
    }
}
