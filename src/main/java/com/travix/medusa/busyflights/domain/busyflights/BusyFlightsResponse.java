package com.travix.medusa.busyflights.domain.busyflights;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BusyFlightsResponse {

    private String supplier;
    private float fare;
    private String departureAirportName;
    private String arrivalAirportName;
    private LocalDateTime outboundDateTime;
    private LocalDateTime inboundDateTime;
    private String airline;
}
