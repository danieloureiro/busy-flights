package com.travix.medusa.busyflights.domain.toughjet;

import com.travix.medusa.busyflights.domain.base.BaseResponse;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * This class represent the ToughJet response.
 *
 * @author Daniel Loureiro (danielloureiro1995@hotmail.com)
 * @since 0.0.1
 */
@Getter
@Setter
public class ToughJetResponse implements BaseResponse {

    private String carrier;
    private double basePrice;
    private double tax;
    private double discount;
    private String departureAirportName;
    private String arrivalAirportName;
    private Instant outboundDateTime;
    private Instant inboundDateTime;

    /**
     * This converts from {@link ToughJetResponse} to {@link BusyFlightsResponse}
     *
     * @return {@link BusyFlightsResponse}
     */
    @Override
    public BusyFlightsResponse toBusyFlightsResponse() {
        BusyFlightsResponse busyFlightsResponse = new BusyFlightsResponse();
        busyFlightsResponse.setSupplier("ToughJet");
        busyFlightsResponse.setFare((float) (((this.basePrice * this.discount) * this.tax) / 100));
        busyFlightsResponse.setDepartureAirportName(this.departureAirportName);
        busyFlightsResponse.setArrivalAirportName(this.arrivalAirportName);
        busyFlightsResponse.setOutboundDateTime(LocalDateTime.ofInstant(this.outboundDateTime, ZoneOffset.UTC));
        busyFlightsResponse.setInboundDateTime(LocalDateTime.ofInstant(this.inboundDateTime, ZoneOffset.UTC));
        busyFlightsResponse.setAirline(this.carrier);
        return busyFlightsResponse;
    }
}
