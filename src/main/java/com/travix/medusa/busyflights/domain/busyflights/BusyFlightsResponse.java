package com.travix.medusa.busyflights.domain.busyflights;

import java.time.LocalDateTime;

public class BusyFlightsResponse {

    private String supplier;
    private float fare;
    private String departureAirportName;
    private String arrivalAirportName;
    private LocalDateTime outboundDateTime;
    private LocalDateTime inboundDateTime;
    private String airline;

    public String getAirline() {
        return airline;
    }

    public void setAirline(final String airline) {
        this.airline = airline;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(final String supplier) {
        this.supplier = supplier;
    }

    public float getFare() {
        return fare;
    }

    public void setFare(final float fare) {
        this.fare = fare;
    }

    public String getDepartureAirportName() {
        return departureAirportName;
    }

    public void setDepartureAirportName(final String departureAirportName) {
        this.departureAirportName = departureAirportName;
    }

    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    public void setArrivalAirportName(final String arrivalAirportName) {
        this.arrivalAirportName = arrivalAirportName;
    }

    public LocalDateTime getOutboundDateTime() {
        return outboundDateTime;
    }

    public void setOutboundDateTime(final LocalDateTime outboundDateTime) {
        this.outboundDateTime = outboundDateTime;
    }

    public LocalDateTime getInboundDateTime() {
        return inboundDateTime;
    }

    public void setInboundDateTime(final LocalDateTime inboundDateTime) {
        this.inboundDateTime = inboundDateTime;
    }

}
