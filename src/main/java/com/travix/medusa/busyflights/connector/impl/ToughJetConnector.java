package com.travix.medusa.busyflights.connector.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.travix.medusa.busyflights.connector.SupplierConnector;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.travix.medusa.busyflights.utils.RequestUtils.builder;

/**
 * This class represents the ToughJet connector through HTTP.
 *
 * @author Daniel Loureiro (danielloureiro1995@hotmail.com)
 * @since 0.0.1
 */
public class ToughJetConnector implements SupplierConnector {
    /**
     * Logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ToughJetConnector.class);

    /**
     * ToughJet find endpoint.
     */
    private static final String FIND_ENDPOINT = "http://localhost:8080/toughJet/flights/find";

    @Override
    public List<BusyFlightsResponse> getFlights(final BusyFlightsRequest busyFlightsRequest) {
        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        ToughJetRequest toughJetRequest = new ToughJetRequest();
        toughJetRequest.fromBusyFlightsRequest(busyFlightsRequest);

        final OkHttpClient httpClient = new OkHttpClient();

        Map<String, String> queryParametersMap = objectMapper.convertValue(toughJetRequest, Map.class);

        Request request = builder(FIND_ENDPOINT, queryParametersMap);

        try (final Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return Collections.emptyList();
            } else {
                //Deserialize http endpoint call
                String result = Objects.requireNonNull(response.body()).string();
                ObjectMapper mapper = new ObjectMapper().findAndRegisterModules().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                final CollectionType typeFactory = mapper.getTypeFactory().constructCollectionType(List.class, ToughJetResponse.class);
                List<ToughJetResponse> crazyAirFlights = mapper.readValue(result, typeFactory);

                return crazyAirFlights.stream().map(ToughJetResponse::toBusyFlightsResponse).collect(Collectors.toList());
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return Collections.emptyList();
    }
}
