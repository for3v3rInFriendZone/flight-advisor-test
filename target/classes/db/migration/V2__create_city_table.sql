DROP TABLE IF EXISTS city;
CREATE TABLE city
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(100)  NOT NULL,
    country     VARCHAR(100)  NOT NULL,
    description VARCHAR(1000) NOT NULL
);

CREATE INDEX city_name_idx
    ON city (name);

INSERT INTO city (id, name, country, description)
VALUES (random_uuid(), 'Belgrade', 'Serbia', 'One of the oldest cities in the Europe'),
       (random_uuid(), 'Paris', 'France', 'Most famous city in Europe');