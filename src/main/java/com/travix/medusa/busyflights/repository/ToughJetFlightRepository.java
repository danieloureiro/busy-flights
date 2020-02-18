package com.travix.medusa.busyflights.repository;

import com.travix.medusa.busyflights.model.ToughJetFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * This repository class represents the {@link ToughJetFlight}.
 *
 * @author Daniel Loureiro (danielloureiro1995@hotmail.com)
 * @since 0.0.1
 */
@RepositoryRestResource
public interface ToughJetFlightRepository extends JpaRepository<ToughJetFlight, Long> {
        /*@Query("SELECT t FROM CrazyAirFlight t "
            + " WHERE t.departureAirportName = :departureAirportName "
            + " and t.arrivalAirportName = :arrivalAirportName "
            + " and t.outboundDateTime = :outboundDateTime "
            + " and t.inboundDateTime = :inboundDateTime "
            + " and t.numberOfAdults = :numberOfAdults")
    List<ToughJetFlight> findFlights(@Param("departureAirportName") final String from,
                                       @Param("arrivalAirportName") final String to,
                                       @Param("outboundDateTime") final LocalDateTime inboundDate,
                                       @Param("inboundDateTime") final LocalDateTime outboundDate,
                                       @Param("numberOfAdults") final int numberOfAdults);*/

    @Query("SELECT t FROM ToughJetFlight t "
            + " WHERE t.departureAirportName = :departureAirportName "
            + " and t.arrivalAirportName = :arrivalAirportName "
            + " and t.numberOfAdults = :numberOfAdults")
    List<ToughJetFlight> findFlights(@Param("departureAirportName") final String from,
                                     @Param("arrivalAirportName") final String to,
                                     @Param("numberOfAdults") final int numberOfAdults);
}
