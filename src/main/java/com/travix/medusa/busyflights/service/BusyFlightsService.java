package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * This class has the methods signature of Busy Flights entity.
 *
 * @author Daniel Loureiro (danielloureiro1995@hotmail.com)
 * @since 0.0.1
 */
public interface BusyFlightsService {
    List<BusyFlightsResponse> findFlights(String origin, String destination, LocalDate departureDate, LocalDate returnDate, int numberOfPassengers) throws IOException;
}
