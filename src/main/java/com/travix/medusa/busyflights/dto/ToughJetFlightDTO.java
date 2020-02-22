package com.travix.medusa.busyflights.dto;

import com.travix.medusa.busyflights.model.ToughJetFlight;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * This class represents the DTO of {@link ToughJetFlight}.
 *
 * @author Daniel Loureiro (danielloureiro1995@hotmail.com)
 * @since 0.0.1
 */
@Getter
@Setter
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
}
