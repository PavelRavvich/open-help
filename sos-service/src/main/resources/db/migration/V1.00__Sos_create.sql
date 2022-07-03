CREATE TABLE IF NOT EXISTS soses
(
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR(255)   NOT NULL,
    status      VARCHAR(255)   NOT NULL, -- NEW | OPEN | PROGRESS | CLOSE
    description VARCHAR(5100)  NOT NULL,
    created_at  TIMESTAMP      NOT NULL,
    updated_at  TIMESTAMP,
    closed_at   TIMESTAMP,
    deleted_at  TIMESTAMP,
    latitude    NUMERIC(10, 6) NOT NULL,
    longitude   NUMERIC(10, 6) NOT NULL,
    user_id     BIGINT         NOT NULL
);