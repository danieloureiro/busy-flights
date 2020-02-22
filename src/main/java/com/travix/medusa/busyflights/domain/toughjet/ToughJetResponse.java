package com.travix.medusa.busyflights.domain.toughjet;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.base.BaseResponse;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class ToughJetResponse extends BaseResponse {

    private String carrier;
    private double basePrice;
    private double tax;
    private double discount;
    private String departureAirportName;
    private String arrivalAirportName;
    private Instant outboundDateTime;
    private Instant inboundDateTime;

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(final String carrier) {
        this.carrier = carrier;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(final double basePrice) {
        this.basePrice = basePrice;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(final double tax) {
        this.tax = tax;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(final double discount) {
        this.discount = discount;
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
