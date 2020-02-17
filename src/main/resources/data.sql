DROP TABLE IF EXISTS crazy_air_flight;
DROP TABLE IF EXISTS tough_jet_flight;

CREATE TABLE crazy_air_flight (
  id IDENTITY AUTO_INCREMENT PRIMARY KEY,
  departure_date TIMESTAMP NOT NULL,
  arrival_date TIMESTAMP NOT NULL,
  airline VARCHAR(20) NOT NULL,
  price FLOAT NOT NULL,
  cabin_class VARCHAR(1) NOT NULL,
  departure_airport_code VARCHAR(3) NOT NULL,
  destination_airport_code VARCHAR(3) NOT NULL,
  number_of_passengers INT NOT NULL
);

CREATE TABLE tough_jet_flight (
  id IDENTITY AUTO_INCREMENT PRIMARY KEY,
  outbound_date_time TIMESTAMP WITH TIME ZONE NOT NULL,
  inbound_date_time TIMESTAMP WITH TIME ZONE NOT NULL,
  carrier VARCHAR(20) NOT NULL,
  base_price FLOAT NOT NULL,
  tax FLOAT NOT NULL,
  discount FLOAT NOT NULL,
  departure_airport_name VARCHAR(3) NOT NULL,
  arrival_airport_name VARCHAR(3) NOT NULL,
  number_of_adults INT NOT NULL
);

INSERT INTO crazy_air_flight (id, departure_date, arrival_date, airline, price, cabin_class, departure_airport_code, destination_airport_code, number_of_passengers) VALUES (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'TAP', 106.18, 'B', 'OPO', 'LGW', 2);
INSERT INTO crazy_air_flight (id, departure_date, arrival_date, airline, price, cabin_class, departure_airport_code, destination_airport_code, number_of_passengers) VALUES (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Rayner', 35.99, 'E', 'OPO', 'STN', 2);
INSERT INTO crazy_air_flight (id, departure_date, arrival_date, airline, price, cabin_class, departure_airport_code, destination_airport_code, number_of_passengers) VALUES (3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'EasyJet', 63.05,  'E',  'OPO', 'LHR', 2);

INSERT INTO tough_jet_flight (id, outbound_date_time, inbound_date_time, carrier, base_price, tax, discount, departure_airport_name, arrival_airport_name, number_of_adults) VALUES (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'TAP', 126.18, 3.5, 20.0, 'OPO', 'LGW', 2);
INSERT INTO tough_jet_flight (id, outbound_date_time, inbound_date_time, carrier, base_price, tax, discount, departure_airport_name, arrival_airport_name, number_of_adults) VALUES (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Rayner', 55.99, 3.5, 20.0, 'OPO', 'STN', 2);
INSERT INTO tough_jet_flight (id, outbound_date_time, inbound_date_time, carrier, base_price, tax, discount, departure_airport_name, arrival_airport_name, number_of_adults) VALUES (3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'EasyJet', 83.05, 3.5, 20.0,  'OPO', 'LHR', 2);
