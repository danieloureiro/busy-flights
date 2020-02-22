package com.travix.medusa.busyflights.domain.crazyair;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.base.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CrazyAirRequest implements BaseRequest {

    private String origin;
    private String destination;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private int passengerCount;

    @Override
    public void fromBusyFlightsRequest(final BusyFlightsRequest busyFlightsRequest) {
        this.origin = busyFlightsRequest.getOrigin();
        this.destination = busyFlightsRequest.getDestination();
        this.departureDate = busyFlightsRequest.getDepartureDate();
        this.returnDate = busyFlightsRequest.getReturnDate();
        this.passengerCount = busyFlightsRequest.getNumberOfPassengers();
    }
}
