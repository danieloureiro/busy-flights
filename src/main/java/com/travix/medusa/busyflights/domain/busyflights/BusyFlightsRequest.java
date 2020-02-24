package com.travix.medusa.busyflights.domain.busyflights;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * This class represent the BusyFlights request.
 *
 * @author Daniel Loureiro (danielloureiro1995@hotmail.com)
 * @since 0.0.1
 */
@Getter
@Setter
public class BusyFlightsRequest {

    private String origin;
    private String destination;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private int numberOfPassengers;
}
