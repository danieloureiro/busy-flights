package com.travix.medusa.busyflights.service.impl;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.model.CrazyAirFlight;
import com.travix.medusa.busyflights.repository.CrazyAirFlightRepository;
import com.travix.medusa.busyflights.service.CrazyAirFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class implements {@link CrazyAirFlightService}.
 *
 * @author Daniel Loureiro (danielloureiro1995@hotmail.com)
 * @since 0.0.1
 */
@Service
public class CrazyAirFlightServiceImpl implements CrazyAirFlightService {

    @Autowired
    CrazyAirFlightRepository crazyAirFlightRepository;

    /**
     * The CrazyAir service implementation constructor.
     *
     * @param crazyAirFlightRepository the CrazyAir repository
     */
    public CrazyAirFlightServiceImpl(final CrazyAirFlightRepository crazyAirFlightRepository) {
        this.crazyAirFlightRepository = crazyAirFlightRepository;
    }

    /**
     * Get all flights from CrazyAir supplier.
     *
     * @return a list of {@link CrazyAirResponse}
     */
    @Override
    public List<CrazyAirResponse> getAllFlights() {
        List<CrazyAirFlight> flights = crazyAirFlightRepository.findAll();
        return buildResponse(flights);
    }

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
    @Override
    public List<CrazyAirResponse> findFlights(final String origin,
                                              final String destination,
                                              final LocalDate departureDate,
                                              final LocalDate returnDate,
                                              final int passengerCount) {

        final LocalDateTime departureDateTime = departureDate.atStartOfDay();
        final LocalDateTime nextDayDepartureDateTime = departureDateTime.plusDays(1);

        final LocalDateTime returnDateTime = returnDate.atStartOfDay();
        final LocalDateTime nextDayReturnDateTime = returnDateTime.plusDays(1);

        final List<CrazyAirFlight> flights =
                this.crazyAirFlightRepository.findFlights(origin, destination, departureDateTime, nextDayDepartureDateTime, returnDateTime, nextDayReturnDateTime, passengerCount);
        return buildResponse(flights);
    }

    /**
     * Build the CrazyAir flights response.
     *
     * @param flights list of {@link CrazyAirFlight} entity
     *
     * @return a list of {@link CrazyAirResponse} flights
     */
    private List<CrazyAirResponse> buildResponse(final List<CrazyAirFlight> flights) {
        return flights.stream().map(CrazyAirFlightServiceImpl::convertToResponse).collect(Collectors.toList());
    }

    /**
     * Convert flight from {@link CrazyAirFlight} to to {@link CrazyAirResponse}.
     *
     * @param flight the {@link CrazyAirFlight} flight
     *
     * @return a {@link CrazyAirResponse} flight
     */
    private static CrazyAirResponse convertToResponse(CrazyAirFlight flight) {
        CrazyAirResponse responseFlight = new CrazyAirResponse();
        responseFlight.setAirline(flight.getAirline());
        responseFlight.setPrice(flight.getPrice());
        responseFlight.setCabinClass(flight.getCabinClass().name());
        responseFlight.setDepartureAirportCode(flight.getDepartureAirportCode());
        responseFlight.setDestinationAirportCode(flight.getDestinationAirportCode());
        responseFlight.setDepartureDate(flight.getDepartureDate());
        responseFlight.setArrivalDate(flight.getArrivalDate());
        return responseFlight;
    }
}
