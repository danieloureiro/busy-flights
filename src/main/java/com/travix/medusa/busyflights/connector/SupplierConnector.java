package com.travix.medusa.busyflights.connector;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;

import java.util.List;

public interface SupplierConnector {
    List<BusyFlightsResponse> getFlights(BusyFlightsRequest busyFlightsRequest);
}
