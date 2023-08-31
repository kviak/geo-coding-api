package ru.kviak.geocodingapi.util.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class GeoCodeErrorResponse {
    private String message;
    private Instant instant;
}
