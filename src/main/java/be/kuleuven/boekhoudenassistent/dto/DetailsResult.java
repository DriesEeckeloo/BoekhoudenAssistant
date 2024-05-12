package be.kuleuven.boekhoudenassistent.dto;

import java.time.LocalDate;

public record DetailsResult(String BUKRS, String GJAHR, String BELNR, String BLART, LocalDate BLDAT, String BUZEI, String BSCHL, LocalDate AUGDT) {
}
