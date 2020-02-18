package com.travix.medusa.busyflights.repository;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.model.CrazyAirFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This repository class represents the {@link CrazyAirFlight}.
 *
 * @author Daniel Loureiro (danielloureiro1995@hotmail.com)
 * @since 0.0.1
 */
@RepositoryRestResource
public interface CrazyAirFlightRepository extends JpaRepository<CrazyAirFlight, Long> {
    /*@Query("SELECT c FROM CrazyAirFlight c "
            + " WHERE c.departureAirportCode = :departureAirportCode "
            + " and c.destinationAirportCode = :destinationAirportCode "
            + " and c.departureDate = :departureDate "
            + " and c.arrivalDate = :arrivalDate "
            + " and c.numberOfPassengers = :numberOfPassengers")
    List<CrazyAirResponse> findFlights(@Param("departureAirportCode") final String origin,
                                       @Param("destinationAirportCode") final String destination,
                                       @Param("departureDate") final LocalDateTime departureDate,
                                       @Param("arrivalDate") final LocalDateTime returnDate,
                                       @Param("numberOfPassengers") final int passengerCount);*/

    @Query("SELECT c FROM CrazyAirFlight c "
            + " WHERE c.departureAirportCode = :departureAirportCode "
            + " and c.destinationAirportCode = :destinationAirportCode "
            + " and c.numberOfPassengers = :numberOfPassengers")
    List<CrazyAirFlight> findFlights(@Param("departureAirportCode") final String origin,
                                       @Param("destinationAirportCode") final String destination,
                                       @Param("numberOfPassengers") final int passengerCount);
}
