package com.travix.medusa.busyflights.service.impl;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.dto.ToughJetFlightDTO;
import com.travix.medusa.busyflights.model.ToughJetFlight;
import com.travix.medusa.busyflights.repository.ToughJetFlightRepository;
import com.travix.medusa.busyflights.service.ToughJetFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public ToughJetFlightServiceImpl(final ToughJetFlightRepository toughJetFlightRepository) {
        this.toughJetFlightRepository = toughJetFlightRepository;
    }

    @Override
    public Page<ToughJetFlightDTO> getAllToughJetFlights(final Pageable pageable) {
        Page<ToughJetFlight> flights = toughJetFlightRepository.findAll(pageable);
        return flights.map(this::convertToDTO);
    }

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
     * Convert a flight from {@link ToughJetFlight} entity to {@link ToughJetFlightDTO}.
     *
     * @param toughJetFlight the ToughJet flights
     *
     * @return {@link ToughJetFlightDTO}
     */
    @Override
    public ToughJetFlightDTO convertToDTO(final ToughJetFlight toughJetFlight) {
        final ToughJetFlightDTO toughJetFlightDTO = new ToughJetFlightDTO();
        toughJetFlightDTO.setId(toughJetFlight.getId());
        toughJetFlightDTO.setOutboundDateTime(toughJetFlight.getOutboundDateTime());
        toughJetFlightDTO.setInboundDateTime(toughJetFlight.getInboundDateTime());
        toughJetFlightDTO.setCarrier(toughJetFlight.getCarrier());
        toughJetFlightDTO.setBasePrice(toughJetFlight.getBasePrice());
        toughJetFlightDTO.setTax(toughJetFlight.getTax());
        toughJetFlightDTO.setDiscount(toughJetFlight.getDiscount());
        toughJetFlightDTO.setDepartureAirportName(toughJetFlight.getDepartureAirportName());
        toughJetFlightDTO.setArrivalAirportName(toughJetFlight.getArrivalAirportName());
        toughJetFlightDTO.setNumberOfAdults(toughJetFlight.getNumberOfAdults());
        return toughJetFlightDTO;
    }

    /**
     * Build the ToughJet flights response.
     *
     * @param flights list of {@link ToughJetFlight} entity
     *
     * @return a list of {@link ToughJetResponse} flights
     */
    private List<ToughJetResponse> buildResponse(final List<ToughJetFlight> flights) {
        return flights.stream().map(ToughJetFlightServiceImpl::apply).collect(Collectors.toList());
    }

    /**
     * Convert flight from {@link ToughJetFlight} to to {@link ToughJetResponse}.
     *
     * @param flight the {@link ToughJetFlight} flight
     *
     * @return a {@link ToughJetResponse} flight
     */
    private static ToughJetResponse apply(ToughJetFlight flight) {
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
