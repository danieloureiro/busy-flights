package com.travix.medusa.busyflights;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.travix.medusa.busyflights.connector.impl.CrazyAirConnector;
import com.travix.medusa.busyflights.connector.impl.ToughJetConnector;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.enums.CabinClassEnum;
import com.travix.medusa.busyflights.model.CrazyAirFlight;
import com.travix.medusa.busyflights.model.ToughJetFlight;
import com.travix.medusa.busyflights.repository.CrazyAirFlightRepository;
import com.travix.medusa.busyflights.repository.ToughJetFlightRepository;
import com.travix.medusa.busyflights.service.impl.CrazyAirFlightServiceImpl;
import com.travix.medusa.busyflights.service.impl.ToughJetFlightServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusyFlightsApplicationTests {

	private BusyFlightsRequest busyFlightsRequest;
	private CrazyAirFlightRepository crazyAirFlightRepository;
	private CrazyAirFlightServiceImpl crazyAirFlightService;
	private ToughJetFlightRepository toughJetFlightRepository;
	private ToughJetFlightServiceImpl toughJetFlightService;

	@Before
	public void contextLoads() throws IOException {
		this.crazyAirFlightRepository = Mockito.mock(CrazyAirFlightRepository.class);
		this.crazyAirFlightService = new CrazyAirFlightServiceImpl(this.crazyAirFlightRepository);

		this.toughJetFlightRepository = org.mockito.Mockito.mock(ToughJetFlightRepository.class);
		this.toughJetFlightService = new ToughJetFlightServiceImpl(this.toughJetFlightRepository);

		ObjectMapper mapper = new ObjectMapper().findAndRegisterModules().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		final InputStream inputStream = new FileInputStream("src/test/resources/busy-flights-request.json");
		this.busyFlightsRequest = mapper.readValue(inputStream, BusyFlightsRequest.class);
	}

	@Test
	public void getAllCrazyAirFlightsTest() {
		// given
		final List<CrazyAirFlight> flights = new ArrayList<>();

		final CrazyAirFlight flight1 = new CrazyAirFlight();

		String crazyAirStringDate = "2020-02-23T09:56:19.797";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
		LocalDateTime crazyAirDate = LocalDateTime.parse(crazyAirStringDate, formatter);

		flight1.setId(1L);
		flight1.setDepartureDate(crazyAirDate);
		flight1.setArrivalDate(crazyAirDate);
		flight1.setAirline("TAP");
		flight1.setPrice((float) 106.18);
		flight1.setCabinClass(CabinClassEnum.B);
		flight1.setDepartureAirportCode("OPO");
		flight1.setDestinationAirportCode("LGW");
		flight1.setNumberOfPassengers(2);
		flights.add(flight1);

		final CrazyAirFlight flight2 = new CrazyAirFlight();
		flight2.setDepartureDate(crazyAirDate);
		flight2.setArrivalDate(crazyAirDate);
		flight2.setAirline("Ryanair");
		flight2.setPrice((float) 35.99);
		flight2.setCabinClass(CabinClassEnum.E);
		flight2.setDepartureAirportCode("OPO");
		flight2.setDestinationAirportCode("STN");
		flight2.setNumberOfPassengers(2);
		flights.add(flight2);

		// when
		Mockito.when(this.crazyAirFlightRepository.findAll()).thenReturn(flights);
		final List<CrazyAirResponse> resultFlights = this.crazyAirFlightService.getAllFlights();

		// then
		Assert.assertEquals(2, resultFlights.size());
		Assert.assertEquals("TAP", resultFlights.get(0).getAirline());
		Assert.assertEquals("Ryanair", resultFlights.get(1).getAirline());

	}

	@Test
	public void findCrazyAirFlightsTest() {
		// given
		final String origin = "OPO";
		final String destination = "STN";
		final LocalDate departureDate = LocalDate.parse("2020-02-23");
		final LocalDate returnDate = LocalDate.parse("2020-02-23");
		final int passengerCount = 2;

		List<CrazyAirFlight> flights = new ArrayList<>();

		String crazyAirStringDate = "2020-02-23 09:56:19";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime crazyAirDate = LocalDateTime.parse(crazyAirStringDate, formatter);

		CrazyAirFlight flight = new CrazyAirFlight();
		flight.setId(1L);
		flight.setDepartureDate(crazyAirDate);
		flight.setArrivalDate(crazyAirDate);
		flight.setAirline("TAP");
		flight.setPrice((float) 106.18);
		flight.setCabinClass(CabinClassEnum.B);
		flight.setDepartureAirportCode("OPO");
		flight.setDestinationAirportCode("LGW");
		flight.setNumberOfPassengers(2);
		flights.add(flight);

		List<CrazyAirResponse> expectedFlights = new ArrayList<>();
		CrazyAirResponse expectedFlight = new CrazyAirResponse();
		expectedFlight.setAirline("TAP");
		expectedFlight.setPrice((float) 106.18);
		expectedFlight.setCabinClass("B");
		expectedFlight.setDepartureAirportCode("OPO");
		expectedFlight.setDestinationAirportCode("LGW");
		expectedFlight.setDepartureDate(crazyAirDate);
		expectedFlight.setArrivalDate(crazyAirDate);
		expectedFlights.add(expectedFlight);

		final LocalDateTime departureDateTime = departureDate.atStartOfDay();
		final LocalDateTime nextDayDepartureDateTime = departureDateTime.plusDays(1);

		final LocalDateTime returnDateTime = returnDate.atStartOfDay();
		final LocalDateTime nextDayReturnDateTime = returnDateTime.plusDays(1);

		// when
		Mockito.when(this.crazyAirFlightRepository.findFlights(origin,
															   destination,
															   departureDateTime,
															   nextDayDepartureDateTime,
															   returnDateTime,
															   nextDayReturnDateTime,
															   passengerCount)).thenReturn(flights);

		final List<CrazyAirResponse> responseFlights = this.crazyAirFlightService.findFlights(origin,
																							  destination,
																							  departureDate,
																							  returnDate,
																							  passengerCount);

		// then
		Assert.assertNotNull(responseFlights);
		Assert.assertEquals(expectedFlights.size(), responseFlights.size());
		IntStream.range(0, responseFlights.size())
			 .forEach(i -> {
				 Assert.assertEquals(expectedFlights.get(i).getAirline(), responseFlights.get(i).getAirline());
				 Assert.assertEquals(String.valueOf(expectedFlights.get(i).getPrice()), String.valueOf(responseFlights.get(i).getPrice()));
				 Assert.assertEquals(expectedFlights.get(i).getCabinClass(), responseFlights.get(i).getCabinClass());
				 Assert.assertEquals(expectedFlights.get(i).getDepartureAirportCode(), responseFlights.get(i).getDepartureAirportCode());
				 Assert.assertEquals(expectedFlights.get(i).getDestinationAirportCode(), responseFlights.get(i).getDestinationAirportCode());
				 Assert.assertEquals(expectedFlights.get(i).getDepartureDate(), responseFlights.get(i).getDepartureDate());
				 Assert.assertEquals(expectedFlights.get(i).getArrivalDate(), responseFlights.get(i).getArrivalDate());
			 })
		;
	}

	@Test
	public void getAllFlightsTest() {
		// given
		final List<ToughJetFlight> flights = new ArrayList<>();

		final ToughJetFlight flight1 = new ToughJetFlight();

		String crazyAirStringDate = "2020-02-23 09:56:19";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime crazyAirDate = LocalDateTime.parse(crazyAirStringDate, formatter);

		flight1.setId(1L);
		flight1.setInboundDateTime(crazyAirDate);
		flight1.setOutboundDateTime(crazyAirDate);
		flight1.setCarrier("TAP");
		flight1.setBasePrice((float) 126.18);
		flight1.setTax((float) 3.5);
		flight1.setDiscount((float) 20);
		flight1.setDepartureAirportName("OPO");
		flight1.setArrivalAirportName("LGW");
		flight1.setNumberOfAdults(2);
		flights.add(flight1);

		final ToughJetFlight flight2 = new ToughJetFlight();
		flight2.setId(1L);
		flight2.setInboundDateTime(crazyAirDate);
		flight2.setOutboundDateTime(crazyAirDate);
		flight2.setCarrier("Ryanair");
		flight2.setBasePrice((float) 126.18);
		flight2.setTax((float) 3.5);
		flight2.setDiscount((float) 20);
		flight2.setDepartureAirportName("OPO");
		flight2.setArrivalAirportName("LGW");
		flight2.setNumberOfAdults(2);
		flights.add(flight2);

		// when
		Mockito.when(this.toughJetFlightRepository.findAll()).thenReturn(flights);
		final List<ToughJetResponse> result = this.toughJetFlightService.getAllFlights();

		// then
		Assert.assertEquals(2, result.size());
		Assert.assertEquals("TAP", result.get(0).getCarrier());
		Assert.assertEquals("Ryanair", result.get(1).getCarrier());
	}

	@Test
	public void findFlightsTest() {
		// given
		final String origin = "OPO";
		final String destination = "STN";
		final LocalDate departureDate = LocalDate.parse("2020-02-23");
		final LocalDate returnDate = LocalDate.parse("2020-02-23");
		final int passengerCount = 2;

		List<ToughJetFlight> flights = new ArrayList<>();

		String toughJetStringDate = "2020-02-23 09:56:19";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime toughJetDate = LocalDateTime.parse(toughJetStringDate, formatter);

		ToughJetFlight flight = new ToughJetFlight();
		flight.setId(1L);
		flight.setOutboundDateTime(toughJetDate);
		flight.setInboundDateTime(toughJetDate);
		flight.setCarrier("TAP");
		flight.setBasePrice((float) 106.18);
		flight.setTax((float) 3.5);
		flight.setDiscount((float) 20);
		flight.setDepartureAirportName("OPO");
		flight.setArrivalAirportName("LGW");
		flight.setNumberOfAdults(2);
		flights.add(flight);

		List<ToughJetResponse> expectedFlights = new ArrayList<>();
		ToughJetResponse expectedFlight = new ToughJetResponse();
		expectedFlight.setOutboundDateTime(toughJetDate.toInstant(ZoneOffset.UTC));
		expectedFlight.setInboundDateTime(toughJetDate.toInstant(ZoneOffset.UTC));
		expectedFlight.setCarrier("TAP");
		expectedFlight.setBasePrice((float) 106.18);
		expectedFlight.setTax((float) 3.5);
		expectedFlight.setDiscount((float) 20);
		expectedFlight.setDepartureAirportName("OPO");
		expectedFlight.setArrivalAirportName("LGW");
		expectedFlights.add(expectedFlight);

		final LocalDateTime departureDateTime = departureDate.atStartOfDay();
		final LocalDateTime nextDayDepartureDateTime = departureDateTime.plusDays(1);

		final LocalDateTime returnDateTime = returnDate.atStartOfDay();
		final LocalDateTime nextDayReturnDateTime = returnDateTime.plusDays(1);

		// when
		Mockito.when(this.toughJetFlightRepository.findFlights(origin,
												 destination,
												 departureDateTime,
												 nextDayDepartureDateTime,
												 returnDateTime,
												 nextDayReturnDateTime,
												 passengerCount)).thenReturn(flights);

		final List<ToughJetResponse> responseFlights = this.toughJetFlightService.findFlights(origin,
																				destination,
																				departureDate,
																				returnDate,
																				passengerCount);

		// then
		Assert.assertNotNull(responseFlights);
		Assert.assertEquals(expectedFlights.size(), responseFlights.size());
		IntStream.range(0, responseFlights.size())
			 .forEach(i -> {
				 Assert.assertEquals(expectedFlights.get(i).getOutboundDateTime(), responseFlights.get(i).getOutboundDateTime());
				 Assert.assertEquals(String.valueOf(expectedFlights.get(i).getInboundDateTime()), String.valueOf(responseFlights.get(i).getInboundDateTime()));
				 Assert.assertEquals(expectedFlights.get(0).getCarrier(), responseFlights.get(i).getCarrier());
				 Assert.assertEquals(String.valueOf(expectedFlights.get(i).getBasePrice()), String.valueOf(responseFlights.get(i).getBasePrice()));
				 Assert.assertEquals(String.valueOf(expectedFlights.get(i).getTax()), String.valueOf(responseFlights.get(i).getTax()));
				 Assert.assertEquals(String.valueOf(expectedFlights.get(i).getDiscount()), String.valueOf(responseFlights.get(i).getDiscount()));
				 Assert.assertEquals(expectedFlights.get(i).getDepartureAirportName(), responseFlights.get(i).getDepartureAirportName());
				 Assert.assertEquals(expectedFlights.get(i).getArrivalAirportName(), responseFlights.get(i).getArrivalAirportName());
			 })
		;
	}

	@Test
	public void getCrazyAirConnectorFlightsTest() {
		// given
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
		String outboundStringDate = "2020-02-23T09:56:19.797";
		String inboundStringDate = "2020-02-23T09:56:19.797";
		LocalDateTime outboundLocalDateTime = LocalDateTime.parse(outboundStringDate, formatter);
		LocalDateTime inboundLocalDateTime = LocalDateTime.parse(inboundStringDate, formatter);

		List<BusyFlightsResponse> expectedFlights = new ArrayList<>();
		BusyFlightsResponse expectedFlight = new BusyFlightsResponse();
		expectedFlight.setSupplier("CrazyAir");
		expectedFlight.setFare((float) 35.99);
		expectedFlight.setDepartureAirportName("OPO");
		expectedFlight.setArrivalAirportName("STN");
		expectedFlight.setOutboundDateTime(outboundLocalDateTime);
		expectedFlight.setInboundDateTime(inboundLocalDateTime);
		expectedFlight.setAirline("Ryanair");
		expectedFlights.add(expectedFlight);

		// when
		final CrazyAirConnector crazyAirConnector =  new CrazyAirConnector();
		final List<BusyFlightsResponse> responseFlights = crazyAirConnector.getFlights(this.busyFlightsRequest);

		// then
		assertBusyFlightsResponse(expectedFlights, responseFlights);
	}

	@Test
	public void getToughJetConnectorFlightsTest() {
		// given
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
		String outboundStringDate = "2020-02-23T09:56:19.797";
		String inboundStringDate = "2020-02-23T09:56:19.797";
		LocalDateTime outboundLocalDateTime = LocalDateTime.parse(outboundStringDate, formatter);
		LocalDateTime inboundLocalDateTime = LocalDateTime.parse(inboundStringDate, formatter);

		List<BusyFlightsResponse> expectedFlights = new ArrayList<>();
		BusyFlightsResponse expectedFlight = new BusyFlightsResponse();
		expectedFlight.setSupplier("ToughJet");
		expectedFlight.setFare((float) 39.193);
		expectedFlight.setDepartureAirportName("OPO");
		expectedFlight.setArrivalAirportName("STN");
		expectedFlight.setOutboundDateTime(outboundLocalDateTime);
		expectedFlight.setInboundDateTime(inboundLocalDateTime);
		expectedFlight.setAirline("Ryanair");
		expectedFlights.add(expectedFlight);

		// when
		final ToughJetConnector toughJetConnector =  new ToughJetConnector();
		final List<BusyFlightsResponse> responseFlights = toughJetConnector.getFlights(this.busyFlightsRequest);

		// then
		assertBusyFlightsResponse(expectedFlights, responseFlights);
	}

	/**
	 * It states whether the lists passed by the parameter are the same.
	 *
	 * @param expectedFlights the expected list to be returned
	 * @param responseFlights the returned list
	 */
	private void assertBusyFlightsResponse(final List<BusyFlightsResponse> expectedFlights,
										   final List<BusyFlightsResponse> responseFlights) {
		Assert.assertNotNull(responseFlights);
		Assert.assertEquals(expectedFlights.size(), responseFlights.size());
		IntStream.range(0, responseFlights.size()).forEach(i -> {
			Assert.assertEquals(expectedFlights.get(i).getSupplier(), responseFlights.get(i).getSupplier());
			Assert.assertEquals(String.valueOf(expectedFlights.get(i).getFare()), String.valueOf(responseFlights.get(i).getFare()));
			Assert.assertEquals(expectedFlights.get(i).getDepartureAirportName(), responseFlights.get(i).getDepartureAirportName());
			Assert.assertEquals(expectedFlights.get(i).getArrivalAirportName(), responseFlights.get(i).getArrivalAirportName());
			Assert.assertEquals(expectedFlights.get(i).getOutboundDateTime(), responseFlights.get(i).getOutboundDateTime());
			Assert.assertEquals(expectedFlights.get(i).getInboundDateTime(), responseFlights.get(i).getInboundDateTime());
			Assert.assertEquals(expectedFlights.get(i).getAirline(), responseFlights.get(i).getAirline());
		});
	}
}
