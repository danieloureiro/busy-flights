package com.travix.medusa.busyflights.domain.crazyair;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.base.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CrazyAirResponse implements BaseResponse {

    private String airline;
    private float price;
    private String cabinClass;
    private String departureAirportCode;
    private String destinationAirportCode;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;

    @Override
    public BusyFlightsResponse toBusyFlightsResponse() {
        BusyFlightsResponse busyFlightsResponse = new BusyFlightsResponse();
        busyFlightsResponse.setSupplier("CrazyAir");
        busyFlightsResponse.setFare(this.price);
        busyFlightsResponse.setDepartureAirportName(this.departureAirportCode);
        busyFlightsResponse.setArrivalAirportName(this.destinationAirportCode);
        busyFlightsResponse.setOutboundDateTime(this.departureDate);
        busyFlightsResponse.setInboundDateTime(this.arrivalDate);
        busyFlightsResponse.setAirline(this.airline);
        return busyFlightsResponse;
    }
}
