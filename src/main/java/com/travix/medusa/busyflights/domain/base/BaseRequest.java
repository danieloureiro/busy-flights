package com.travix.medusa.busyflights.domain.base;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;

/**
 * This class represent the base request.
 *
 * @author Daniel Loureiro (danielloureiro1995@hotmail.com)
 * @since 0.0.1
 */
public interface BaseRequest {
    /**
     * Converts from {@link BusyFlightsRequest} for a given request type.
     *
     * @param busyFlightsRequest the request
     */
    void fromBusyFlightsRequest(BusyFlightsRequest busyFlightsRequest);
}
