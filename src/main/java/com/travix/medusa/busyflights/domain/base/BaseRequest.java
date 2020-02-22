package com.travix.medusa.busyflights.domain.base;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;

public abstract class BaseRequest {
    public abstract void fromBusyFlightsRequest(BusyFlightsRequest busyFlightsRequest);
}
