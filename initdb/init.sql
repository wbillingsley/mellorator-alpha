CREATE DATABASE mellorator WITH ENCODING 'UTF8';

\c mellorator

CREATE TABLE IF NOT EXISTS melluser (
    id UUID,
    name VARCHAR NOT NULL,
    PRIMARY KEY(id)
);


CREATE TABLE IF NOT EXISTS animal (
    id UUID,
    name VARCHAR NOT NULL  
);