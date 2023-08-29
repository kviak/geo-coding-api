package ru.kviak.geocodingapi.dto.map_dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

public class AddressInfoDto {
    private Summary summary;
    private List<Address> addresses;

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public static class Summary {
        @JsonProperty("queryTime")
        private int queryTime;
        @JsonProperty("numResults")
        private int numResults;

        // Геттеры и сеттеры для полей queryTime и numResults
        // ...

        // Остальные методы и поля, если необходимы
        // ...
    }

    public static class Address {
        private AddressData address;
        private String position;
        private String id;

        public AddressData getAddress() {
            return address;
        }

        public void setAddress(AddressData address) {
            this.address = address;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        // Остальные методы и поля, если необходимы
        // ...
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

        // Геттеры и сеттеры для всех полей
        // ...

        // Остальные методы и поля, если необходимы
        // ...
    }

    public static class BoundingBox {
        @JsonProperty("northEast")
        private String northEast;
        @JsonProperty("southWest")
        private String southWest;
        private String entity;

        // Геттеры и сеттеры для всех полей
        // ...

        // Остальные методы и поля, если необходимы
        // ...
    }
}
