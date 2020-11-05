DROP TABLE IF EXISTS city;
CREATE TABLE city
(
    id      UUID PRIMARY KEY,
    name    VARCHAR(250),
    country VARCHAR(250)
);

CREATE INDEX city_name_idx
ON city(name);

INSERT INTO city (id, name, country)
VALUES (random_uuid(), 'Belgrade', 'Serbia'),
       (random_uuid(), 'Paris', 'France');