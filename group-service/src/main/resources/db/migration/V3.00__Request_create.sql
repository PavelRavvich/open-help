CREATE TABLE IF NOT EXISTS requests
(
    id          SERIAL PRIMARY KEY,
    created_at  TIMESTAMP    NOT NULL,
    updated_at  TIMESTAMP,
    title       VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    status      VARCHAR(255) NOT NULL,
    user_id     BIGINT       NOT NULL,
    group_id    BIGINT REFERENCES groups (id)
);