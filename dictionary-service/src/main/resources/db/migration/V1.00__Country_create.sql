CREATE TABLE IF NOT EXISTS countries
(
    id           BIGSERIAL PRIMARY KEY,
    title        VARCHAR(255) NOT NULL,
    country_code VARCHAR(255) NOT NULL,
    iso_code     VARCHAR(255) NOT NULL,
    phone_code   VARCHAR(255) NOT NULL
);