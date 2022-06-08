CREATE TABLE IF NOT EXISTS access
(
    id             BIGSERIAL PRIMARY KEY,
    title          VARCHAR(255) NOT NULL,
    entity_type    VARCHAR(255) NOT NULL,
    operation_type VARCHAR(255) NOT NULL,
    role_id        BIGINT REFERENCES roles (id)
);

CREATE TABLE IF NOT EXISTS roles_access
(
    role_id   BIGINT REFERENCES roles (id),
    access_id BIGINT REFERENCES access (id)
);

CREATE UNIQUE INDEX IF NOT EXISTS ui_role_access ON roles_access (role_id, access_id);
