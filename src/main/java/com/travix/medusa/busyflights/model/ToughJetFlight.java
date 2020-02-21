package com.travix.medusa.busyflights.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "tough_jet_flight")
public class ToughJetFlight {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="outbound_date_time")
    private LocalDateTime outboundDateTime;

    @Column(name="inbound_date_time")
    private LocalDateTime inboundDateTime;

    @Column(name="carrier")
    private String carrier;

    @Column(name="base_price")
    private Float basePrice;

    @Column(name="tax")
    private Float tax;

    @Column(name="discount")
    private Float discount;

    @Column(name="departure_airport_name")
    private String departureAirportName;

    @Column(name="arrival_airport_name")
    private String arrivalAirportName;

    @Column(name="number_of_adults")
    private Integer numberOfAdults;

    public ToughJetFlight(final Long id,
                          final LocalDateTime outboundDateTime,
                          final LocalDateTime inboundDateTime,
                          final String carrier,
                          final Float basePrice,
                          final Float tax,
                          final Float discount,
                          final String departureAirportName,
                          final String destinationAirportName,
                          final Integer numberOfAdults) {
        this.id = id;
        this.outboundDateTime = outboundDateTime;
        this.inboundDateTime = inboundDateTime;
        this.carrier = carrier;
        this.basePrice = basePrice;
        this.tax = tax;
        this.discount = discount;
        this.departureAirportName = departureAirportName;
        this.arrivalAirportName = destinationAirportName;
        this.numberOfAdults = numberOfAdults;
    }

    public ToughJetFlight() {
    }

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

    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    public void setArrivalAirportName(final String arrivalAirportName) {
        this.arrivalAirportName = arrivalAirportName;
    }

    public Integer getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(final Integer numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }
}
