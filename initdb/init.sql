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
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS emails (
    id UUID,
    melluser UUID,
    email VARCHAR NOT NULL UNIQUE,
    validationcode VARCHAR NOT NULL, -- a code to send out in email to verify it
    validated BIGINT, -- whether / when the email address has been validated
)

create unique index email_lower on emails (lower(email))

-- Animals may belong to an organisation
CREATE TABLE IF NOT EXISTS organization (
    id UUID,
    name VARCHAR NOT NULL, 
    created BIGINT,
    PRIMARY KEY(id)
)

-- Used for permission system
CREATE TABLE IF NOT EXISTS organizationrole (
    melluser UUID,
    organization UUID,
    orgrole VARCHAR NOT NULL, -- 

)


CREATE TABLE IF NOT EXISTS animal (
    id UUID,
    name VARCHAR NOT NULL,
    kind VARCHAR,
    melluser UUID,
    organisation UUID,
    created BIGINT  
);

CREATE TABLE IF NOT EXISTS animalassessment (
    id UUID,
    
)