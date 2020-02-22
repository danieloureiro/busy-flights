package com.travix.medusa.busyflights.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "tough_jet_flight")
@Getter
@Setter
@NoArgsConstructor
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
}
