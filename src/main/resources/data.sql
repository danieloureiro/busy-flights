DROP TABLE IF EXISTS crazy_air_flight;
DROP TABLE IF EXISTS tough_jet_flight;

CREATE TABLE crazy_air_flight(
  id                        IDENTITY AUTO_INCREMENT PRIMARY KEY,
  departure_date            TIMESTAMP NOT NULL,
  arrival_date              TIMESTAMP NOT NULL,
  airline                   VARCHAR(20) NOT NULL,
  price                     FLOAT NOT NULL,
  cabin_class               ENUM('E', 'B') NOT NULL,
  departure_airport_code    VARCHAR(3) NOT NULL,
  destination_airport_code  VARCHAR(3) NOT NULL,
  number_of_passengers      INT NOT NULL
);

create TABLE tough_jet_flight (
  id                     IDENTITY AUTO_INCREMENT PRIMARY KEY,
  outbound_date_time     TIMESTAMP WITH TIME ZONE NOT NULL,
  inbound_date_time      TIMESTAMP WITH TIME ZONE NOT NULL,
  carrier                VARCHAR(20) NOT NULL,
  base_price             FLOAT NOT NULL,
  tax                    FLOAT NOT NULL,
  discount               FLOAT NOT NULL,
  departure_airport_name VARCHAR(3) NOT NULL,
  arrival_airport_name   VARCHAR(3) NOT NULL,
  number_of_adults       INT NOT NULL
);

INSERT INTO crazy_air_flight (id, departure_date, arrival_date, airline, price, cabin_class, departure_airport_code, destination_airport_code, number_of_passengers) values (1, current_timestamp(), current_timestamp(), 'TAP', 106.18, 'B', 'OPO', 'LGW', 2);
INSERT INTO crazy_air_flight (id, departure_date, arrival_date, airline, price, cabin_class, departure_airport_code, destination_airport_code, number_of_passengers) values (2, current_timestamp(), current_timestamp(), 'Rayner', 35.99, 'E', 'OPO', 'STN', 2);
INSERT INTO crazy_air_flight (id, departure_date, arrival_date, airline, price, cabin_class, departure_airport_code, destination_airport_code, number_of_passengers) values (3, current_timestamp(), current_timestamp(), 'EasyJet', 63.05,  'E',  'OPO', 'LHR', 2);

INSERT INTO tough_jet_flight (id, outbound_date_time, inbound_date_time, carrier, base_price, tax, discount, departure_airport_name, arrival_airport_name, number_of_adults) values (1, current_timestamp(), current_timestamp(), 'TAP', 126.18, 3.5, 20.0, 'OPO', 'LGW', 2);
INSERT INTO tough_jet_flight (id, outbound_date_time, inbound_date_time, carrier, base_price, tax, discount, departure_airport_name, arrival_airport_name, number_of_adults) values (2, current_timestamp(), current_timestamp(), 'Rayner', 55.99, 3.5, 20.0, 'OPO', 'STN', 2);
INSERT INTO tough_jet_flight (id, outbound_date_time, inbound_date_time, carrier, base_price, tax, discount, departure_airport_name, arrival_airport_name, number_of_adults) values (3, current_timestamp(), current_timestamp(), 'EasyJet', 83.05, 3.5, 20.0,  'OPO', 'LHR', 2);
