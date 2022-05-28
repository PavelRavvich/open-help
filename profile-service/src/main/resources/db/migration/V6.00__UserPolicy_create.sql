CREATE TABLE IF NOT EXISTS user_policies
(
    read_user_list           BOOLEAN NOT NULL,
    read_any_user_details    BOOLEAN NOT NULL,
    read_any_user_action_log BOOLEAN NOT NULL,
    create_user              BOOLEAN NOT NULL,
    update_own_user          BOOLEAN NOT NULL,
    update_any_user          BOOLEAN NOT NULL,
    delete_own_user          BOOLEAN NOT NULL,
    delete_any_user          BOOLEAN NOT NULL,
    role_system_name         VARCHAR(255) REFERENCES roles (system_name)
);