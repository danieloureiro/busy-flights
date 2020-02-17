package com.travix.medusa.busyflights.service.impl;

import com.travix.medusa.busyflights.dto.CrazyAirFlightDTO;
import com.travix.medusa.busyflights.model.CrazyAirFlight;
import com.travix.medusa.busyflights.repository.CrazyAirFlightRepository;
import com.travix.medusa.busyflights.service.CrazyAirFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Page<CrazyAirFlightDTO> getAllCrazyAirFlights(final Pageable pageable) {
        Page<CrazyAirFlight> crazyAirFlights = crazyAirFlightRepository.findAll(pageable);
        return crazyAirFlights.map(this::convertToDTO);
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
}
