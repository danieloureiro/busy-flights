package com.travix.medusa.busyflights.dto;

import com.travix.medusa.busyflights.enums.CabinClassEnum;
import com.travix.medusa.busyflights.model.CrazyAirFlight;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * This class represents the DTO of {@link CrazyAirFlight}.
 *
 * @author Daniel Loureiro (danielloureiro1995@hotmail.com)
 * @since 0.0.1
 */
@Getter
@Setter
public class CrazyAirFlightDTO {

    private Long id;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private String airline;
    private Float price;
    private CabinClassEnum cabinClass;
    private String departureAirportCode;
    private String destinationAirportCode;
    private Integer numberOfPassengers;
}
