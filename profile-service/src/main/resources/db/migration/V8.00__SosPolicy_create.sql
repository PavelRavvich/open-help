CREATE TABLE IF NOT EXISTS sos_policies
(
    id                 BIGSERIAL PRIMARY KEY,
    is_read_sos_list      BOOLEAN NOT NULL,
    is_read_full_sos_list BOOLEAN NOT NULL,
    is_create_sos         BOOLEAN NOT NULL,
    is_update_own_sos     BOOLEAN NOT NULL,
    is_update_any_sos     BOOLEAN NOT NULL,
    is_delete_own_sos     BOOLEAN NOT NULL,
    is_delete_any_sos     BOOLEAN NOT NULL,
    role_system_name   VARCHAR(255) REFERENCES roles (system_name)
);