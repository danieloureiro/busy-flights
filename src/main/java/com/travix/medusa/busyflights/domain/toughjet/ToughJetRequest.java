package com.travix.medusa.busyflights.domain.toughjet;

import com.travix.medusa.busyflights.domain.base.BaseRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * This class represent the ToughJet request.
 *
 * @author Daniel Loureiro (danielloureiro1995@hotmail.com)
 * @since 0.0.1
 */
@Getter
@Setter
public class ToughJetRequest implements BaseRequest {

    private String from;
    private String to;
    private LocalDate outboundDate;
    private LocalDate inboundDate;
    private int numberOfAdults;

    /**
     * This converts a given {@link BusyFlightsRequest} to {@link ToughJetRequest}
     *
     * @param busyFlightsRequest the given request
     */
    @Override
    public void fromBusyFlightsRequest(final BusyFlightsRequest busyFlightsRequest) {
        this.from = busyFlightsRequest.getOrigin();
        this.to = busyFlightsRequest.getDestination();
        this.outboundDate = busyFlightsRequest.getDepartureDate();
        this.inboundDate = busyFlightsRequest.getReturnDate();
        this.numberOfAdults = busyFlightsRequest.getNumberOfPassengers();
    }
}
