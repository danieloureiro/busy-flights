package com.travix.medusa.busyflights.domain.toughjet;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.base.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Getter
@Setter
public class ToughJetResponse implements BaseResponse {

    private String carrier;
    private double basePrice;
    private double tax;
    private double discount;
    private String departureAirportName;
    private String arrivalAirportName;
    private Instant outboundDateTime;
    private Instant inboundDateTime;

    @Override
    public BusyFlightsResponse toBusyFlightsResponse() {
        BusyFlightsResponse busyFlightsResponse = new BusyFlightsResponse();
        busyFlightsResponse.setSupplier("ToughJet");
        busyFlightsResponse.setFare((float)(((this.basePrice * this.discount) * this.tax)/100));
        busyFlightsResponse.setDepartureAirportName(this.departureAirportName);
        busyFlightsResponse.setArrivalAirportName(this.arrivalAirportName);
        busyFlightsResponse.setOutboundDateTime(LocalDateTime.ofInstant(this.outboundDateTime, ZoneOffset.UTC));
        busyFlightsResponse.setInboundDateTime(LocalDateTime.ofInstant(this.inboundDateTime, ZoneOffset.UTC));
        busyFlightsResponse.setAirline(this.carrier);
        return busyFlightsResponse;
    }
}
