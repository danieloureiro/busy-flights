package com.travix.medusa.busyflights.domain.busyflights;

import java.time.Instant;

public class BusyFlightsResponse {

    private String supplier;
    private float fare;
    private String departureAirportName;
    private String arrivalAirportName;
    private Instant outboundDateTime;
    private Instant inboundDateTime;
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

    public Instant getOutboundDateTime() {
        return outboundDateTime;
    }

    public void setOutboundDateTime(final Instant outboundDateTime) {
        this.outboundDateTime = outboundDateTime;
    }

    public Instant getInboundDateTime() {
        return inboundDateTime;
    }

    public void setInboundDateTime(final Instant inboundDateTime) {
        this.inboundDateTime = inboundDateTime;
    }

}
