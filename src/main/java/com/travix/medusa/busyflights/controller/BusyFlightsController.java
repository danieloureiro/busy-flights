package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.BusyFlightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/busyFlights")
public class BusyFlightsController {

    @Autowired
    private BusyFlightsService busyFlightsService;

    public BusyFlightsController(final BusyFlightsService busyFlightsService) {
        this.busyFlightsService = busyFlightsService;
    }

    @GetMapping(value = "/find")
    public List<BusyFlightsResponse> findFlight(@RequestParam String origin,
                                                @RequestParam String destination,
                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate,
                                                @RequestParam int numberOfPassengers) throws IOException {

        return this.busyFlightsService.findFlights(origin, destination, departureDate, returnDate, numberOfPassengers);
    }
}
