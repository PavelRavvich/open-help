CREATE TABLE IF NOT EXISTS tasks
(
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR(255)  NOT NULL,
    description VARCHAR(5100) NOT NULL,
    status      VARCHAR(255)  NOT NULL, -- NEW | PROGRESS | DONE | DELETED
    created_at  TIMESTAMP     NOT NULL,
    updated_at  TIMESTAMP,
    closed_at   TIMESTAMP,
    deleted_at  TIMESTAMP,
    author_id   BIGINT NOT NULL,
    executor_id BIGINT NOT NULL,
    story_id    BIGINT REFERENCES stories (id)
);