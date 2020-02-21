package com.travix.medusa.busyflights.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.service.BusyFlightsService;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
                                                 final int numberOfPassengers) throws IOException {

        final OkHttpClient httpClient = new OkHttpClient();

        final String url = "http://localhost:8080/crazyAir/flights/find";

        Map<String, String> queryParametersMap = buildCrazyAirQueryParametersMap(origin, destination, departureDate, returnDate, numberOfPassengers);

        Request request = requestBuilder(url, queryParametersMap);

        try (final Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return Collections.emptyList();
            } else {
                //Deserialize http endpoint call
                String result = Objects.requireNonNull(response.body()).string();
                ObjectMapper mapper = new ObjectMapper().findAndRegisterModules().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                final CollectionType typeFactory = mapper.getTypeFactory().constructCollectionType(List.class, CrazyAirResponse.class);
                List<CrazyAirResponse> crazyAirFlights = mapper.readValue(result, typeFactory);

                return crazyAirFlights.stream().map(BusyFlightsServiceImpl::apply).collect(Collectors.toList());
            }
        }


    }

    private static BusyFlightsResponse apply(CrazyAirResponse crazyAirFlights) {
        BusyFlightsResponse flights = new BusyFlightsResponse();
        flights.setSupplier("CrazyAir");
        flights.setFare((float)crazyAirFlights.getPrice());
        flights.setDepartureAirportName(crazyAirFlights.getDepartureAirportCode());
        flights.setArrivalAirportName(crazyAirFlights.getDestinationAirportCode());
        flights.setOutboundDateTime(crazyAirFlights.getDepartureDate().toInstant(ZoneOffset.UTC));
        flights.setInboundDateTime(crazyAirFlights.getArrivalDate().toInstant(ZoneOffset.UTC));
        flights.setAirline(crazyAirFlights.getAirline());
        return flights;
    }

    /**
     * Build the query parameters in a {@link Map} in order to get CrazyAir flights.
     *
     * @param origin             the flight airport origin IATA code
     * @param destination        the flight airport destination IATA code
     * @param departureDate      the flight departure date
     * @param returnDate         the flight return date
     * @param numberOfPassengers the flight number of passengers
     * @return a {@link Map} of query parameters
     */
    @NotNull
    private Map<String, String> buildCrazyAirQueryParametersMap(final String origin,
                                                                final String destination,
                                                                final LocalDate departureDate,
                                                                final LocalDate returnDate,
                                                                final int numberOfPassengers) {
        Map<String, String> queryParametersMap = new HashMap<>();
        queryParametersMap.put("origin", origin);
        queryParametersMap.put("destination", destination);
        queryParametersMap.put("departureDate", departureDate.toString());
        queryParametersMap.put("returnDate", returnDate.toString());
        queryParametersMap.put("passengerCount", String.valueOf(numberOfPassengers));
        return queryParametersMap;
    }

    /**
     * Build request object.
     * <p>
     * @param url              the endpoint where call request
     * @param queryParameters  the map with the query parameters
     * @return {@link Request} object
     */
    public static Request requestBuilder(String url, Map<String,String> queryParameters) {
        HttpUrl.Builder httpBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        if (queryParameters != null) {
            for(Map.Entry<String, String> param : queryParameters.entrySet()) {
                httpBuilder.addQueryParameter(param.getKey(), param.getValue());
            }
        }
        Request request = new Request.Builder().url(httpBuilder.build()).build();
        return request;
    }
}
