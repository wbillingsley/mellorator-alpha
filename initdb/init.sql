CREATE DATABASE mellorator WITH ENCODING 'UTF8';

-- TODO: secure password from environment variable
-- CREATE USER mellpg WITH PASSWORD 'example';

-- GRANT ALL PRIVILEGES ON DATABASE mellorator to mellpg;

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