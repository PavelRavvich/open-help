CREATE TABLE IF NOT EXISTS groups
(
    id            SERIAL PRIMARY KEY,
    members_limit INT,
    status        VARCHAR(255)   NOT NULL, -- OPEN | CLOSED
    title         VARCHAR(255)   NOT NULL,
    description   VARCHAR(5100)  NOT NULL,
    created_at    TIMESTAMP      NOT NULL,
    updated_at    TIMESTAMP,
    expired_at    TIMESTAMP      NOT NULL,
    deleted_at    TIMESTAMP      NOT NULL,
    latitude      NUMERIC(10, 6),
    longitude     NUMERIC(10, 6),
    user_id       BIGINT         NOT NULL
);