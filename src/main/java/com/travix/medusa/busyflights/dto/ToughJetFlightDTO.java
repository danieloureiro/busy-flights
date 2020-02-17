package com.travix.medusa.busyflights.dto;

import com.travix.medusa.busyflights.enums.IATACodesEnum;
import com.travix.medusa.busyflights.model.ToughJetFlight;

import java.time.LocalDateTime;

/**
 * This class represents the DTO of {@link ToughJetFlight}.
 *
 * @author Daniel Loureiro (danielloureiro1995@hotmail.com)
 * @since 0.0.1
 */
public class ToughJetFlightDTO {

    private Long id;
    private LocalDateTime outboundDateTime;
    private LocalDateTime inboundDateTime;
    private String carrier;
    private Float basePrice;
    private Float tax;
    private Float discount;
    private String departureAirportName;
    private String arrivalAirportName;
    private Integer numberOfAdults;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public LocalDateTime getOutboundDateTime() {
        return outboundDateTime;
    }

    public void setOutboundDateTime(final LocalDateTime outboundDateTime) {
        this.outboundDateTime = outboundDateTime;
    }

    public LocalDateTime getInboundDateTime() {
        return inboundDateTime;
    }

    public void setInboundDateTime(final LocalDateTime inboundDateTime) {
        this.inboundDateTime = inboundDateTime;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(final String carrier) {
        this.carrier = carrier;
    }

    public Float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(final Float basePrice) {
        this.basePrice = basePrice;
    }

    public Float getTax() {
        return tax;
    }

    public void setTax(final Float tax) {
        this.tax = tax;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(final Float discount) {
        this.discount = discount;
    }

    public String getDepartureAirportName() {
        return departureAirportName;
    }

    public void setDepartureAirportName(final String departureAirportName) {
        this.departureAirportName = departureAirportName;
    }

    public String getDestinationAirportName() {
        return arrivalAirportName;
    }

    public void setDestinationAirportName(final String destinationAirportName) {
        this.arrivalAirportName = destinationAirportName;
    }

    public Integer getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(final Integer numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }
}
