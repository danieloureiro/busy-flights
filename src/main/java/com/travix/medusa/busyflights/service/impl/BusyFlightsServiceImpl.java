package com.travix.medusa.busyflights.service.impl;

import com.travix.medusa.busyflights.connector.ConnectorFactory;
import com.travix.medusa.busyflights.connector.SupplierConnector;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.BusyFlightsService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class implements {@link BusyFlightsService}.
 * <p>
 * @author Daniel Loureiro (danielloureiro1995@hotmail.com)
 * @since 0.0.1
 */
@Service
public class BusyFlightsServiceImpl implements BusyFlightsService {
    @Override
    public List<BusyFlightsResponse> findFlights(final String origin,
                                                 final String destination,
                                                 final LocalDate departureDate,
                                                 final LocalDate returnDate,
                                                 final int numberOfPassengers) {


        BusyFlightsRequest busyFlightsRequest = new BusyFlightsRequest();
        busyFlightsRequest.setOrigin(origin);
        busyFlightsRequest.setDestination(destination);
        busyFlightsRequest.setDepartureDate(departureDate);
        busyFlightsRequest.setReturnDate(returnDate);
        busyFlightsRequest.setNumberOfPassengers(numberOfPassengers);

        List<BusyFlightsResponse> flights = Collections.synchronizedList(new ArrayList<BusyFlightsResponse>());

        final List<SupplierConnector> suppliers = ConnectorFactory.getAllConnectors();
        suppliers.parallelStream().forEach(supplier -> flights.addAll(supplier.getFlights(busyFlightsRequest)));

        return flights;
    }
}
