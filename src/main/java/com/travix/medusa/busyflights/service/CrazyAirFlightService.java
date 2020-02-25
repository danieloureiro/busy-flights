package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;

import java.time.LocalDate;
import java.util.List;

/**
 * This class has the methods signature of Crazy Air flights entity.
 *
 * @author Daniel Loureiro (danielloureiro1995@hotmail.com)
 * @since 0.0.1
 */
public interface CrazyAirFlightService {
    /**
     * Get all flights from CrazyAir supplier.
     *
     * @return a list of {@link CrazyAirResponse}
     */
    List<CrazyAirResponse> getAllFlights();

    /**
     * Find flights from CrazyAir supplier by given parameters.
     *
     * @param origin         the IATA code of origin airport
     * @param destination    the IATA code of destination airport
     * @param departureDate  the ISO_LOCAL_DATE format departure date
     * @param returnDate     the ISO_LOCAL_DATE format return date
     * @param passengerCount the number of passengers
     *
     * @return a list of {@link CrazyAirResponse} flights
     */
    List<CrazyAirResponse> findFlights(String origin,
                                       String destination,
                                       LocalDate departureDate,
                                       LocalDate returnDate,
                                       int passengerCount);
}
