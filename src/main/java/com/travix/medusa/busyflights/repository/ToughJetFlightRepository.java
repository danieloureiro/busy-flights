package com.travix.medusa.busyflights.repository;

import com.travix.medusa.busyflights.model.ToughJetFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This repository class represents the {@link ToughJetFlight}.
 *
 * @author Daniel Loureiro (danielloureiro1995@hotmail.com)
 * @since 0.0.1
 */
@RepositoryRestResource
public interface ToughJetFlightRepository extends JpaRepository<ToughJetFlight, Long> {
    @Query(""
            + " SELECT t FROM ToughJetFlight t "
            + " WHERE t.departureAirportName = :departureAirportName "
            + "     AND t.arrivalAirportName = :arrivalAirportName "
            + "     AND t.outboundDateTime BETWEEN :outboundDateTime AND :nextDayOutboundDateTime"
            + "     AND t.inboundDateTime BETWEEN :inboundDateTime AND :nextDayInboundDateTime"
            + "     AND t.numberOfAdults = :numberOfAdults")
    List<ToughJetFlight> findFlights(@Param("departureAirportName") final String from,
                                     @Param("arrivalAirportName") final String to,
                                     @Param("outboundDateTime") final LocalDateTime outboundDateTime,
                                     @Param("nextDayOutboundDateTime") final LocalDateTime nextDayOutboundDateTime,
                                     @Param("inboundDateTime") final LocalDateTime inboundDateTime,
                                     @Param("nextDayInboundDateTime") final LocalDateTime nextDayInboundDateTime,
                                     @Param("numberOfAdults") final int numberOfAdults);
}
