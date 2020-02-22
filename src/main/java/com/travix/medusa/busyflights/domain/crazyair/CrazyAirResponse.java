package com.travix.medusa.busyflights.domain.crazyair;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.base.BaseResponse;

import java.time.LocalDateTime;

public class CrazyAirResponse extends BaseResponse {

    private String airline;
    private float price;
    private String cabinClass;
    private String departureAirportCode;
    private String destinationAirportCode;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;

    public String getAirline() {
        return airline;
    }

    public void setAirline(final String airline) {
        this.airline = airline;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final float price) {
        this.price = price;
    }

    public String getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(final String cabinClass) {
        this.cabinClass = cabinClass;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(final String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    public void setDestinationAirportCode(final String destinationAirportCode) {
        this.destinationAirportCode = destinationAirportCode;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(final LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(final LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

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
