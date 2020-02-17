package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.dto.CrazyAirFlightDTO;
import com.travix.medusa.busyflights.service.CrazyAirFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crazyAir")
public class CrazyAirController {

    @Autowired
    private CrazyAirFlightService crazyAirFlightService;

    public CrazyAirController(final CrazyAirFlightService crazyAirFlightService) {
        this.crazyAirFlightService = crazyAirFlightService;
    }

    @GetMapping(value = "/flights")
    public Page<CrazyAirFlightDTO> crazyAirFlights(Pageable pageable) {
        return crazyAirFlightService.getAllCrazyAirFlights(pageable);
    }
}
