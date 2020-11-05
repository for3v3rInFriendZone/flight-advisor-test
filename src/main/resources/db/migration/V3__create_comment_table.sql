DROP TABLE IF EXISTS comment;
CREATE TABLE comment
(
    id           UUID PRIMARY KEY,
    text         VARCHAR(250)             NOT NULL,
    city_id      UUID                     NOT NULL,
    createdDate  TIMESTAMP WITH TIME ZONE NOT NULL,
    modifiedDate TIMESTAMP WITH TIME ZONE,

    FOREIGN KEY (city_id) REFERENCES city(id)
);