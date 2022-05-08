CREATE TABLE IF NOT EXISTS stories
(
    id                  BIGSERIAL PRIMARY KEY,
    title               VARCHAR(255)  NOT NULL,
    type                VARCHAR(255)  NOT NULL, -- ORDER | REQUEST
    status              VARCHAR(255)  NOT NULL, -- NEW | OPEN | PROGRESS | CLOSE
    description         VARCHAR(5100) NOT NULL,
    created_at          TIMESTAMP     NOT NULL,
    updated_at          TIMESTAMP,
    closed_at           TIMESTAMP,
    deleted_at          TIMESTAMP,
    author_id           BIGINT NOT NULL,
    current_location_id BIGINT NOT NULL,
    exodus_location_id  BIGINT NOT NULL,
    target_location_id  BIGINT NOT NULL
);