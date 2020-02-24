package com.travix.medusa.busyflights.connector;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.enums.Supplier;

import java.util.List;

/**
 * This class represents the base supplier connector.
 *
 * @author Daniel Loureiro (danielloureiro1995@hotmail.com)
 * @since 0.0.1
 */
public interface SupplierConnector {
    /**
     * Get all flights from registered {@link Supplier}
     *
     * @param busyFlightsRequest the request
     * @return a list of {@link BusyFlightsResponse}
     */
    List<BusyFlightsResponse> getFlights(BusyFlightsRequest busyFlightsRequest);
}
