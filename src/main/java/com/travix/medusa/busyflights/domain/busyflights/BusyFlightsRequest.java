package com.travix.medusa.busyflights.domain.busyflights;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BusyFlightsRequest {

    private String origin;
    private String destination;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private int numberOfPassengers;
}
