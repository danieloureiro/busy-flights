package com.travix.medusa.busyflights.repository;

import com.travix.medusa.busyflights.model.ToughJetFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * This repository class represents the {@link ToughJetFlight}.
 *
 * @author Daniel Loureiro (danielloureiro1995@hotmail.com)
 * @since 0.0.1
 */
@RepositoryRestResource
public interface ToughJetFlightRepository extends JpaRepository<ToughJetFlight, Long> {
}
