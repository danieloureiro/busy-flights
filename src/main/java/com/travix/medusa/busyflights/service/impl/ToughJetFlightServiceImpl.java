package com.travix.medusa.busyflights.service.impl;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.model.ToughJetFlight;
import com.travix.medusa.busyflights.repository.ToughJetFlightRepository;
import com.travix.medusa.busyflights.service.ToughJetFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class implements {@link ToughJetFlightService}.
 *
 * @author Daniel Loureiro (danielloureiro1995@hotmail.com)
 * @since 0.0.1
 */
@Service
public class ToughJetFlightServiceImpl implements ToughJetFlightService {
    @Autowired
    private ToughJetFlightRepository toughJetFlightRepository;

    /**
     * The ToughJet service implementation constructor.
     *
     * @param toughJetFlightRepository the ToughJet repository
     */
    public ToughJetFlightServiceImpl(final ToughJetFlightRepository toughJetFlightRepository) {
        this.toughJetFlightRepository = toughJetFlightRepository;
    }

    /**
     * Get all flights from ToughJet supplier.
     *
     * @return a list of {@link ToughJetResponse}
     */
    @Override
    public List<ToughJetResponse> getAllFlights() {
        List<ToughJetFlight> flights = toughJetFlightRepository.findAll();
        return buildResponse(flights);
    }

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
    @Override
    public List<ToughJetResponse> findFlights(final String from,
                                              final String to,
                                              final LocalDate outboundDate,
                                              final LocalDate inboundDate,
                                              final int numberOfAdults) {

        final LocalDateTime outboundDateTime = outboundDate.atStartOfDay();
        final LocalDateTime nextDayOutboundDateTime = outboundDateTime.plusDays(1);

        final LocalDateTime inboundDateTime = inboundDate.atStartOfDay();
        final LocalDateTime nextDayInboundDateTime = inboundDateTime.plusDays(1);

        final List<ToughJetFlight> flights =
                this.toughJetFlightRepository.findFlights(from, to, outboundDateTime, nextDayOutboundDateTime, inboundDateTime, nextDayInboundDateTime, numberOfAdults);
        return buildResponse(flights);
    }

    /**
     * Build the ToughJet flights response.
     *
     * @param flights list of {@link ToughJetFlight} entity
     *
     * @return a list of {@link ToughJetResponse} flights
     */
    private List<ToughJetResponse> buildResponse(final List<ToughJetFlight> flights) {
        return flights.stream().map(ToughJetFlightServiceImpl::convertToResponse).collect(Collectors.toList());
    }

    /**
     * Convert flight from {@link ToughJetFlight} to to {@link ToughJetResponse}.
     *
     * @param flight the {@link ToughJetFlight} flight
     *
     * @return a {@link ToughJetResponse} flight
     */
    private static ToughJetResponse convertToResponse(ToughJetFlight flight) {
        ToughJetResponse responseFlight = new ToughJetResponse();
        responseFlight.setCarrier(flight.getCarrier());
        responseFlight.setBasePrice(flight.getBasePrice());
        responseFlight.setTax(flight.getTax());
        responseFlight.setDiscount(flight.getDiscount());
        responseFlight.setDepartureAirportName(flight.getDepartureAirportName());
        responseFlight.setArrivalAirportName(flight.getArrivalAirportName());
        responseFlight.setOutboundDateTime(flight.getOutboundDateTime().toInstant(ZoneOffset.UTC));
        responseFlight.setInboundDateTime(flight.getInboundDateTime().toInstant(ZoneOffset.UTC));
        return responseFlight;
    }
}
