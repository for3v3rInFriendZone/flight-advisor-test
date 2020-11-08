DROP TABLE IF EXISTS route;
CREATE TABLE route
(
    id                       UUID PRIMARY KEY,
    airline_code             VARCHAR(3)     NOT NULL,
    airline_id               VARCHAR(30),
    source_airport_code      VARCHAR(4)     NOT NULL,
    source_airport_id        VARCHAR(20)    NOT NULL,
    destination_airport_code VARCHAR(4)     NOT NULL,
    destination_airport_id   VARCHAR(20)    NOT NULL,
    codeshare                VARCHAR(1),
    stops                    INT            NOT NULL,
    equipment                VARCHAR(50)    NOT NULL,
    price                    DECIMAL(8, 2)  NOT NULL,
    distance                 DECIMAL(10, 2) NOT NULL
);

CREATE INDEX route_source_airport_id_idx
    ON route (source_airport_id);

CREATE INDEX route_destination_airport_code_idx
    ON route (destination_airport_code);