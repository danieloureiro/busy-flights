package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.dto.ToughJetFlightDTO;
import com.travix.medusa.busyflights.service.ToughJetFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/busyFlights")
public class BusyFlightsController {

    @Autowired
    private ToughJetFlightService toughJetFlightService;

    public BusyFlightsController(final ToughJetFlightService toughJetFlightService) {
        this.toughJetFlightService = toughJetFlightService;
    }

    @GetMapping(value = "/search")
    public Page<ToughJetFlightDTO> busyFlightsResponses(Pageable pageable) {
        return this.toughJetFlightService.getAllToughJetFlights(pageable);
    }
}
