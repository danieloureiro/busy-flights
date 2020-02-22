package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.dto.CrazyAirFlightDTO;
import com.travix.medusa.busyflights.model.CrazyAirFlight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

/**
 * This class has the methods signature of Crazy Air flights entity.
 *
 * @author Daniel Loureiro (danielloureiro1995@hotmail.com)
 * @since 0.0.1
 */
public interface CrazyAirFlightService {

    Page<CrazyAirFlightDTO> getAllFlights(final Pageable pageable);

    CrazyAirFlightDTO convertToDTO(final CrazyAirFlight crazyAirFlight);

    List<CrazyAirResponse> findFlights(String origin,
                                       String destination,
                                       LocalDate departureDate,
                                       LocalDate returnDate,
                                       int passengerCount);
}
