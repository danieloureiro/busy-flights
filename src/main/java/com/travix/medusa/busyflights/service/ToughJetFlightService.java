package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.dto.ToughJetFlightDTO;
import com.travix.medusa.busyflights.model.ToughJetFlight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

/**
 * This class has the methods signature of Tough Jet flights entity.
 *
 * @author Daniel Loureiro (danielloureiro1995@hotmail.com)
 * @since 0.0.1
 */
public interface ToughJetFlightService {

    Page<ToughJetFlightDTO> getAllToughJetFlights(final Pageable pageable);

    ToughJetFlightDTO convertToDTO(final ToughJetFlight toughJetFlight);

    List<ToughJetResponse> findFlights(String from, String to, LocalDate outboundDate, LocalDate inboundDate, int numberOfAdults);
}
