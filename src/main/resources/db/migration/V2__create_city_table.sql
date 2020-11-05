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
VALUES ('7ca8941f-9c6d-4ede-ab9b-3c9214da1e1b', 'Belgrade', 'Serbia', 'One of the oldest cities in the Europe'),
       (random_uuid(), 'Paris', 'France', 'Most famous city in Europe');