DROP TABLE IF EXISTS airport;
CREATE TABLE airport
(
    id                INT PRIMARY KEY,
    name              VARCHAR(100)    NOT NULL,
    city              VARCHAR(100)    NOT NULL,
    country           VARCHAR(100)    NOT NULL,
    iata              VARCHAR(3),
    icao              VARCHAR(4),
    latitude          DECIMAL(30, 20) NOT NULL,
    longitude         DECIMAL(30, 20) NOT NULL,
    altitude          INT             NOT NULL,
    timezone          VARCHAR(10)     NOT NULL,
    dst               VARCHAR(10)     NOT NULL,
    database_timezone VARCHAR(50)     NOT NULL,
    type              VARCHAR(200),
    source            VARCHAR(200)
);

CREATE INDEX airport_iata_idx
ON airport(iata);

CREATE INDEX airport_icao_idx
    ON airport(icao);