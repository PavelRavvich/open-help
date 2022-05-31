CREATE TABLE IF NOT EXISTS role_policies
(
    id                BIGSERIAL PRIMARY KEY,
    is_read_role_list BOOLEAN NOT NULL,
    is_create_role    BOOLEAN NOT NULL,
    is_update_role    BOOLEAN NOT NULL,
    is_delete_role    BOOLEAN NOT NULL,
    role_system_name  VARCHAR(255) REFERENCES roles (system_name)
);