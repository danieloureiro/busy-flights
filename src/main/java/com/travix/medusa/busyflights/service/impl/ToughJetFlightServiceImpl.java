package com.travix.medusa.busyflights.service.impl;

import com.travix.medusa.busyflights.dto.ToughJetFlightDTO;
import com.travix.medusa.busyflights.model.ToughJetFlight;
import com.travix.medusa.busyflights.repository.ToughJetFlightRepository;
import com.travix.medusa.busyflights.service.ToughJetFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        toughJetFlightDTO.setDestinationAirportName(toughJetFlight.getDestinationAirportName());
        toughJetFlightDTO.setNumberOfAdults(toughJetFlight.getNumberOfAdults());
        return toughJetFlightDTO;
    }
}