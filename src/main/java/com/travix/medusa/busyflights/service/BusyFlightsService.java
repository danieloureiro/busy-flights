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
    /**
     * Find flights from all suppliers by given parameters.
     *
     * @param origin             the IATA code of origin airport
     * @param destination        the IATA code of destination airport
     * @param departureDate      the ISO_LOCAL_DATE format departure date
     * @param returnDate         the ISO_LOCAL_DATE format return date
     * @param numberOfPassengers the number of passengers
     *
     * @return a list of {@link BusyFlightsResponse} flights
     *
     * @throws IOException
     */
    List<BusyFlightsResponse> findFlights(String origin,
                                          String destination,
                                          LocalDate departureDate,
                                          LocalDate returnDate,
                                          int numberOfPassengers) throws IOException;
}
