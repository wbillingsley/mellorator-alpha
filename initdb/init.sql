CREATE DATABASE mellorator WITH ENCODING 'UTF8';

-- TODO: secure password from environment variable
-- CREATE USER mellpg WITH PASSWORD 'example';

-- GRANT ALL PRIVILEGES ON DATABASE mellorator to mellpg;

\c mellorator

-- The users of our app
CREATE TABLE IF NOT EXISTS melluser (
    id UUID,
    name VARCHAR NOT NULL,
    created BIGINT,
    lastUpdate BIGINT,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS emails (
    id UUID,
    melluser UUID,
    email VARCHAR NOT NULL UNIQUE,
    validationcode VARCHAR NOT NULL, -- a code to send out in email to verify it
    timeSent BIGINT,
    validated BIGINT, -- whether / when the email address has been validated
    PRIMARY KEY(id)
);

create unique index email_lower on emails (lower(email));

-- Animals may belong to an organisation
CREATE TABLE IF NOT EXISTS organization (
    id UUID,
    name VARCHAR NOT NULL, 
    created BIGINT,
    PRIMARY KEY(id)
);

-- Used for permission system
CREATE TABLE IF NOT EXISTS organizationrole (
    melluser UUID,
    organization UUID,
    roles VARCHAR ARRAY NOT NULL,
    lastUpdate BIGINT,
    CONSTRAINT fk_melluser FOREIGN KEY (melluser) REFERENCES melluser(id),
    CONSTRAINT fk_organization FOREIGN KEY (organization) REFERENCES organization(id)
);


CREATE TABLE IF NOT EXISTS animal (
    id UUID,
    name VARCHAR NOT NULL,
    species VARCHAR,
    melluser UUID,
    organisation UUID,
    created BIGINT,
    lastUpdate BIGINT,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS animalassessment (
    id UUID,
    animal UUID,
    score DOUBLE PRECISION ARRAY[16],
    confidence DOUBLE PRECISION ARRAY[16],
    clientTime BIGINT,
    lastUpdate BIGINT,
    CONSTRAINT fk_animal FOREIGN KEY (animal) REFERENCES animal(id),
    PRIMARY KEY(id)
);