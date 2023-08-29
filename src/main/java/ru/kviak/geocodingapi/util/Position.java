package ru.kviak.geocodingapi.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Position {
    private BigDecimal lat;
    private BigDecimal lon;

    // Геттеры и сеттеры (необязательно, но рекомендуется)
}
