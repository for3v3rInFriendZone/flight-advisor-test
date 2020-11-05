DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id         UUID PRIMARY KEY,
    first_name VARCHAR(250),
    last_name  VARCHAR(250),
    username   VARCHAR(250) UNIQUE NOT NULL,
    password   VARCHAR(250)        NOT NULL
);

INSERT INTO user (id, first_name, last_name, username, password)
VALUES (random_uuid(), 'Aliko', 'Dangote', 'adsdsa', 'adassdsa'),
       (random_uuid(), 'Bill', 'Gates', 'fqwfwf', 'qwdqwqw');