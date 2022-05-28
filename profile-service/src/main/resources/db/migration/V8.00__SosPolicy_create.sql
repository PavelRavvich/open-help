CREATE TABLE IF NOT EXISTS sos_policies
(
    read_sos_list      BOOLEAN NOT NULL,
    read_full_sos_list BOOLEAN NOT NULL,
    create_sos         BOOLEAN NOT NULL,
    update_own_sos     BOOLEAN NOT NULL,
    update_any_sos     BOOLEAN NOT NULL,
    delete_own_sos     BOOLEAN NOT NULL,
    delete_any_sos     BOOLEAN NOT NULL,
    role_system_name   VARCHAR(255) REFERENCES roles (system_name)
);