package com.travix.medusa.busyflights.connector.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.travix.medusa.busyflights.connector.SupplierConnector;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.travix.medusa.busyflights.utils.RequestUtils.builder;

public class CrazyAirConnector implements SupplierConnector {

    private static final String FIND_ENDPOINT = "http://localhost:8080/crazyAir/flights/find";

    @Override
    public List<BusyFlightsResponse> getFlights(final BusyFlightsRequest busyFlightsRequest) {

        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        CrazyAirRequest crazyAirRequest = new CrazyAirRequest();
        crazyAirRequest.fromBusyFlightsRequest(busyFlightsRequest);

        final OkHttpClient httpClient = new OkHttpClient();

        Map<String, String> queryParametersMap = objectMapper.convertValue(crazyAirRequest, Map.class);

        Request request = builder(FIND_ENDPOINT, queryParametersMap);

        try (final Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return Collections.emptyList();
            } else {
                //Deserialize http endpoint call
                String result = Objects.requireNonNull(response.body()).string();
                ObjectMapper mapper = new ObjectMapper().findAndRegisterModules().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                final CollectionType typeFactory = mapper.getTypeFactory().constructCollectionType(List.class, CrazyAirResponse.class);
                List<CrazyAirResponse> crazyAirFlights = mapper.readValue(result, typeFactory);

                return crazyAirFlights.stream().map(CrazyAirResponse::toBusyFlightsResponse).collect(Collectors.toList());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
