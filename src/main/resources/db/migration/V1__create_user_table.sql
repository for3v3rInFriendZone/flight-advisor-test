DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id         UUID PRIMARY KEY,
    first_name VARCHAR(100)        NOT NULL,
    last_name  VARCHAR(100)        NOT NULL,
    username   VARCHAR(100) UNIQUE NOT NULL,
    password   VARCHAR(200)        NOT NULL,
    type       VARCHAR(50)        NOT NULL
);

INSERT INTO user (id, first_name, last_name, username, password, type)
VALUES (random_uuid(), 'Aliko', 'Dangote', 'root1', '$2a$10$zPVE2u6hin2m2XdYblh3sO1MhWcbJpWi1HPJsS1c3.lzX7P.jUelK', 'ADMIN'),
       (random_uuid(), 'Bill', 'Gates', 'root2', '$2a$10$zPVE2u6hin2m2XdYblh3sO1MhWcbJpWi1HPJsS1c3.lzX7P.jUelK', 'ADMIN');