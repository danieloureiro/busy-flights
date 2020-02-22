package com.travix.medusa.busyflights.domain.base;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;

public interface BaseRequest {
    void fromBusyFlightsRequest(BusyFlightsRequest busyFlightsRequest);
}
