package com.travix.medusa.busyflights.model;

import com.travix.medusa.busyflights.enums.CabinClassEnum;
import com.travix.medusa.busyflights.enums.IATACodesEnum;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "crazy_air_flight")
public class CrazyAirFlight {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="departure_date")
    private LocalDateTime departureDate;

    @Column(name="arrival_date")
    private LocalDateTime arrivalDate;

    @Column(name="airline")
    private String airline;

    @Column(name="price")
    private Float price;

    @Column(name="cabin_class")
    //private CabinClassEnum cabinClass;
    private String cabinClass;

    @Column(name="departure_airport_code")
    //@Enumerated(EnumType.STRING)
    private String departureAirportCode;

    @Column(name="destination_airport_code")
    //@Enumerated(EnumType.STRING)
    private String destinationAirportCode;

    @Column(name="number_of_passengers")
    private Integer numberOfPassengers;

    public CrazyAirFlight(final Long id,
                          final LocalDateTime departureDate,
                          final LocalDateTime arrivalDate,
                          final String airline,
                          final Float price,
                          final String cabinClass,
                          final String departureAirportCode,
                          final String destinationAirportCode,
                          final Integer numberOfPassengers) {
        this.id = id;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.airline = airline;
        this.price = price;
        this.cabinClass = cabinClass;
        this.departureAirportCode = departureAirportCode;
        this.destinationAirportCode = destinationAirportCode;
        this.numberOfPassengers = numberOfPassengers;
    }

    public CrazyAirFlight() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
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

    public String getAirline() {
        return airline;
    }

    public void setAirline(final String airline) {
        this.airline = airline;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(final Float price) {
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

    public Integer getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(final Integer numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }
}
