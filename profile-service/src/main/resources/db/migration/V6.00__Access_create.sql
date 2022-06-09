CREATE TABLE IF NOT EXISTS accesses
(
    id             BIGSERIAL PRIMARY KEY,
    title          VARCHAR(255) NOT NULL,
    entity_type    VARCHAR(255) NOT NULL,
    operation_type VARCHAR(255) NOT NULL,
    role_id        BIGINT REFERENCES roles (id)
);

CREATE TABLE IF NOT EXISTS roles_accesses
(
    role_id   BIGINT REFERENCES roles (id),
    access_id BIGINT REFERENCES accesses (id)
);

CREATE UNIQUE INDEX IF NOT EXISTS ui_role_access ON roles_accesses (role_id, access_id);
