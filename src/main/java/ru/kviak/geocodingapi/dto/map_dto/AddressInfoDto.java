package ru.kviak.geocodingapi.dto.map_dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
public class AddressInfoDto {
    private Summary summary;
    private List<Address> addresses;
    @Getter
    @Setter
    public static class Summary {
        @JsonProperty("queryTime")
        private int queryTime;
        @JsonProperty("numResults")
        private int numResults;
    }
    @Getter
    @Setter
    public static class Address {
        private AddressData address;
        private String position;
        private String id;
    }
    @ToString
    @Getter
    @Setter
    public static class AddressData {
        private String street;
        @JsonProperty("streetName")
        private String streetName;
        @JsonProperty("countryCode")
        private String countryCode;
        @JsonProperty("countrySubdivision")
        private String countrySubdivision;
        @JsonProperty("countrySecondarySubdivision")
        private String countrySecondarySubdivision;
        @JsonProperty("countryTertiarySubdivision")
        private String countryTertiarySubdivision;
        private String municipality;
        @JsonProperty("postalCode")
        private String postalCode;
        private String country;
        @JsonProperty("countryCodeISO3")
        private String countryCodeISO3;
        @JsonProperty("freeformAddress")
        private String freeformAddress;
        @JsonProperty("boundingBox")
        private BoundingBox boundingBox;
        @JsonProperty("localName")
        private String localName;
    }
    @Getter
    @Setter
    public static class BoundingBox {
        @JsonProperty("northEast")
        private String northEast;
        @JsonProperty("southWest")
        private String southWest;
        private String entity;
    }
}
