CREATE TABLE IF NOT EXISTS user_policies
(
    id                          BIGSERIAL PRIMARY KEY,
    is_read_user_list           BOOLEAN NOT NULL,
    is_read_any_user_details    BOOLEAN NOT NULL,
    is_read_any_user_action_log BOOLEAN NOT NULL,
    is_create_user              BOOLEAN NOT NULL,
    is_update_own_user          BOOLEAN NOT NULL,
    is_update_any_user          BOOLEAN NOT NULL,
    is_delete_own_user          BOOLEAN NOT NULL,
    is_delete_any_user          BOOLEAN NOT NULL,
    role_system_name            VARCHAR(255) REFERENCES roles (system_name)
);