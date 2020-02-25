package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;

import java.time.LocalDate;
import java.util.List;

/**
 * This class has the methods signature of Tough Jet flights entity.
 *
 * @author Daniel Loureiro (danielloureiro1995@hotmail.com)
 * @since 0.0.1
 */
public interface ToughJetFlightService {
    /**
     * Get all flights from ToughJet supplier.
     *
     * @return a list of {@link ToughJetResponse}
     */
    List<ToughJetResponse> getAllFlights();

    /**
     * Find flights from CrazyAir supplier by given parameters.
     *
     * @param from           the IATA code of origin airport
     * @param to             the IATA code of destination airport
     * @param outboundDate   the ISO_LOCAL_DATE format outbound date
     * @param inboundDate    the ISO_LOCAL_DATE format inbound date
     * @param numberOfAdults the number of passengers
     *
     * @return a list of {@link CrazyAirResponse} flights
     */
    List<ToughJetResponse> findFlights(String from, String to, LocalDate outboundDate, LocalDate inboundDate, int numberOfAdults);
}
