package com.travix.medusa.busyflights.domain.busyflights;

import com.travix.medusa.busyflights.enums.IATACodesEnum;

import java.time.Instant;

public class BusyFlightsResponse {

    private String supplier;
    private float fare;
    private IATACodesEnum departureAirportName;
    private IATACodesEnum arrivalAirportName;
    private Instant outboundDateTime;
    private Instant inboundDateTime;

    private String airplane;

    public String getAirplane() {
        return airplane;
    }

    public void setAirplane(final String airplane) {
        this.airplane = airplane;
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

    public IATACodesEnum getDepartureAirportName() {
        return departureAirportName;
    }

    public void setDepartureAirportName(final IATACodesEnum departureAirportName) {
        this.departureAirportName = departureAirportName;
    }

    public IATACodesEnum getArrivalAirportName() {
        return arrivalAirportName;
    }

    public void setArrivalAirportName(final IATACodesEnum arrivalAirportName) {
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
