package com.travix.medusa.busyflights.domain.crazyair;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.base.BaseRequest;

import java.time.LocalDate;

public class CrazyAirRequest extends BaseRequest {

    private String origin;
    private String destination;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private int passengerCount;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(final String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(final String destination) {
        this.destination = destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(final LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(final LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(final int passengerCount) {
        this.passengerCount = passengerCount;
    }

    @Override
    public void fromBusyFlightsRequest(final BusyFlightsRequest busyFlightsRequest) {
        this.origin = busyFlightsRequest.getOrigin();
        this.destination = busyFlightsRequest.getDestination();
        this.departureDate = busyFlightsRequest.getDepartureDate();
        this.returnDate = busyFlightsRequest.getReturnDate();
        this.passengerCount = busyFlightsRequest.getNumberOfPassengers();
    }
}
