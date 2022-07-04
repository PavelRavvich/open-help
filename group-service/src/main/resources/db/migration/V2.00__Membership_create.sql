CREATE TABLE IF NOT EXISTS memberships
(
    id         SERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    user_id    BIGINT    NOT NULL,
    group_id   BIGINT REFERENCES groups (id)
);