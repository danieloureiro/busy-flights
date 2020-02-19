package com.travix.medusa.busyflights.service.impl;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.dto.CrazyAirFlightDTO;
import com.travix.medusa.busyflights.model.CrazyAirFlight;
import com.travix.medusa.busyflights.repository.CrazyAirFlightRepository;
import com.travix.medusa.busyflights.service.CrazyAirFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
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

    public CrazyAirFlightServiceImpl(final CrazyAirFlightRepository crazyAirFlightRepository) {
        this.crazyAirFlightRepository = crazyAirFlightRepository;
    }

    @Override
    public Page<CrazyAirFlightDTO> getAllFlights(final Pageable pageable) {
        Page<CrazyAirFlight> crazyAirFlights = crazyAirFlightRepository.findAll(pageable);
        return crazyAirFlights.map(this::convertToDTO);
    }

    @Override
    public List<CrazyAirResponse> searchFlights(final CrazyAirRequest crazyAirRequest) {
        return Collections.emptyList();
    }

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

    @Override
    public CrazyAirFlightDTO convertToDTO(final CrazyAirFlight crazyAirFlight) {
        final CrazyAirFlightDTO crazyAirFlightDTO = new CrazyAirFlightDTO();
        crazyAirFlightDTO.setId(crazyAirFlight.getId());
        crazyAirFlightDTO.setDepartureDate(crazyAirFlight.getDepartureDate());
        crazyAirFlightDTO.setArrivalDate(crazyAirFlight.getArrivalDate());
        crazyAirFlightDTO.setAirline(crazyAirFlight.getAirline());
        crazyAirFlightDTO.setPrice(crazyAirFlight.getPrice());
        crazyAirFlightDTO.setCabinClass(crazyAirFlight.getCabinClass());
        crazyAirFlightDTO.setDepartureAirportCode(crazyAirFlight.getDepartureAirportCode());
        crazyAirFlightDTO.setDestinationAirportCode(crazyAirFlight.getDestinationAirportCode());
        crazyAirFlightDTO.setNumberOfPassengers(crazyAirFlight.getNumberOfPassengers());
        return crazyAirFlightDTO;
    }

    private List<CrazyAirResponse> buildResponse(final List<CrazyAirFlight> flights) {
        return flights.stream().map(CrazyAirFlightServiceImpl::apply).collect(Collectors.toList());
    }

    private static CrazyAirResponse apply(CrazyAirFlight flight) {
        CrazyAirResponse responseFlight = new CrazyAirResponse();
        responseFlight.setAirline(flight.getAirline());
        responseFlight.setPrice(flight.getPrice());
        responseFlight.setCabinClass(flight.getCabinClass().name());
        responseFlight.setDepartureAirportCode(flight.getDepartureAirportCode());
        responseFlight.setDestinationAirportCode(flight.getDestinationAirportCode());
        responseFlight.setDepartureDate(flight.getDepartureDate().toString());
        responseFlight.setArrivalDate(flight.getArrivalDate().toString());
        return responseFlight;
    }
}
