package com.travix.medusa.busyflights.domain.base;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;

/**
 * This class represent the base response.
 *
 * @author Daniel Loureiro (danielloureiro1995@hotmail.com)
 * @since 0.0.1
 */
public interface BaseResponse {
    /**
     * Converts to {@link BusyFlightsResponse} flight.
     *
     * @return a {@link BusyFlightsResponse} flight
     */
    BusyFlightsResponse toBusyFlightsResponse();
}
