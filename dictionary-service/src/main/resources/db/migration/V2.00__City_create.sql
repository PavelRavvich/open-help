CREATE TABLE IF NOT EXISTS cities
(
    id         BIGSERIAL PRIMARY KEY,
    title      VARCHAR(255) NOT NULL,
    phone_code VARCHAR(255) NOT NULL,
    country_id BIGINT REFERENCES countries (id)
);