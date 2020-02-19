package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.CrazyAirFlightService;
import com.travix.medusa.busyflights.service.ToughJetFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/busyFlights")
public class BusyFlightsController {

    @Autowired
    private ToughJetFlightService toughJetFlightService;

    @Autowired
    private CrazyAirFlightService crazyAirFlightService;

    public BusyFlightsController(final ToughJetFlightService toughJetFlightService, final CrazyAirFlightService crazyAirFlightService) {
        this.toughJetFlightService = toughJetFlightService;
        this.crazyAirFlightService = crazyAirFlightService;
    }

    @GetMapping(value = "/find")
    public List<BusyFlightsResponse> findFlight(@RequestParam String origin,
                                                @RequestParam String destination,
                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate,
                                                @RequestParam int numberOfPassengers) {
        //this.toughJetFlightService.findFlights();
        //this.crazyAirFlightService.findFlights();
        return Collections.emptyList();
    }
}
