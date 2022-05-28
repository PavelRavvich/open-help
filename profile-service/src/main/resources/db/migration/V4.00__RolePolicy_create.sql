CREATE TABLE IF NOT EXISTS role_policies
(
    read_role_list   BOOLEAN NOT NULL,
    create_role      BOOLEAN NOT NULL,
    update_role      BOOLEAN NOT NULL,
    delete_role      BOOLEAN NOT NULL,
    role_system_name VARCHAR(255) REFERENCES roles (system_name)
);