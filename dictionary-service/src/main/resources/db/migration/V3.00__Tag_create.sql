CREATE TABLE IF NOT EXISTS tags
(
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    system_name VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP    NOT NULL,
    deleted_at  TIMESTAMP    NOT NULL
);