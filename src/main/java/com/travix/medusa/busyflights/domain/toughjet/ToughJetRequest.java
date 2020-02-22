package com.travix.medusa.busyflights.domain.toughjet;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.base.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ToughJetRequest implements BaseRequest {

    private String from;
    private String to;
    private LocalDate outboundDate;
    private LocalDate inboundDate;
    private int numberOfAdults;

    @Override
    public void fromBusyFlightsRequest(final BusyFlightsRequest busyFlightsRequest) {
        this.from = busyFlightsRequest.getOrigin();
        this.to = busyFlightsRequest.getDestination();
        this.outboundDate = busyFlightsRequest.getDepartureDate();
        this.inboundDate = busyFlightsRequest.getReturnDate();
        this.numberOfAdults = busyFlightsRequest.getNumberOfPassengers();
    }
}
