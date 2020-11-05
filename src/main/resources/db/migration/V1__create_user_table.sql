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
VALUES (random_uuid(), 'Aliko', 'Dangote', 'adsdsa', 'adassdsa', 'ADMIN'),
       (random_uuid(), 'Bill', 'Gates', 'fqwfwf', 'qwdqwqw', 'ADMIN');