-- liquibase formatted sql

-- changeset jitzerttok51:1
-- Create users table

CREATE TABLE users (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    created_date TIMESTAMP(6) NOT NULL,
    modified_date TIMESTAMP(6) not null,

    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    hash VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    sex VARCHAR(255) NOT NULL check (sex in ('MALE','FEMALE')),
    date_of_birth DATE NOT NULL,

    primary key (id)
);

CREATE UNIQUE INDEX index_username ON users (username);
CREATE UNIQUE INDEX index_email ON users (email);
