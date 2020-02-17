package com.travix.medusa.busyflights.dto;

import com.travix.medusa.busyflights.enums.CabinClassEnum;
import com.travix.medusa.busyflights.enums.IATACodesEnum;
import com.travix.medusa.busyflights.model.CrazyAirFlight;

import java.time.LocalDateTime;

/**
 * This class represents the DTO of {@link CrazyAirFlight}.
 *
 * @author Daniel Loureiro (danielloureiro1995@hotmail.com)
 * @since 0.0.1
 */
public class CrazyAirFlightDTO {

    private Long id;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private String airline;
    private Float price;
    private String cabinClass;
    private String departureAirportCode;
    private String destinationAirportCode;
    private Integer numberOfPassengers;

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
