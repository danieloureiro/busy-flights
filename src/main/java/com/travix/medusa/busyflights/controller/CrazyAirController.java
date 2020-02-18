package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.dto.CrazyAirFlightDTO;
import com.travix.medusa.busyflights.service.CrazyAirFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/crazyAir")
public class CrazyAirController {

    @Autowired
    private CrazyAirFlightService crazyAirFlightService;

    public CrazyAirController(final CrazyAirFlightService crazyAirFlightService) {
        this.crazyAirFlightService = crazyAirFlightService;
    }

    @GetMapping(value = "/flights")
    public Page<CrazyAirFlightDTO> crazyAirFlights(final Pageable pageable) {
        return crazyAirFlightService.getAllCrazyAirFlights(pageable);
    }

    @GetMapping(value = "/flight/search")
    public List<CrazyAirResponse> crazyAirFlightSearch(@RequestBody final CrazyAirRequest crazyAirRequest) {
        return crazyAirFlightService.searchCrazyAirFlights(crazyAirRequest);
    }

}
