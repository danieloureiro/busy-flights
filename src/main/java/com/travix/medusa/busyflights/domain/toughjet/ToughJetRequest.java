package com.travix.medusa.busyflights.domain.toughjet;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.base.BaseRequest;

import java.time.LocalDate;

public class ToughJetRequest extends BaseRequest {

    private String from;
    private String to;
    private LocalDate outboundDate;
    private LocalDate inboundDate;
    private int numberOfAdults;

    public String getFrom() {
        return from;
    }

    public void setFrom(final String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(final String to) {
        this.to = to;
    }

    public LocalDate getOutboundDate() {
        return outboundDate;
    }

    public void setOutboundDate(final LocalDate outboundDate) {
        this.outboundDate = outboundDate;
    }

    public LocalDate getInboundDate() {
        return inboundDate;
    }

    public void setInboundDate(final LocalDate inboundDate) {
        this.inboundDate = inboundDate;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(final int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    @Override
    public void fromBusyFlightsRequest(final BusyFlightsRequest busyFlightsRequest) {
        this.from = busyFlightsRequest.getOrigin();
        this.to = busyFlightsRequest.getDestination();
        this.outboundDate = busyFlightsRequest.getDepartureDate();
        this.inboundDate = busyFlightsRequest.getReturnDate();
        this.numberOfAdults = busyFlightsRequest.getNumberOfPassengers();
    }
}
