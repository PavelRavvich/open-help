CREATE TABLE IF NOT EXISTS group_policies
(
    id                    BIGSERIAL PRIMARY KEY,
    is_read_group_list       BOOLEAN NOT NULL,
    is_read_any_group_detail BOOLEAN NOT NULL,
    is_send_group_request    BOOLEAN NOT NULL,
    is_send_group_invite     BOOLEAN NOT NULL,
    is_create_group          BOOLEAN NOT NULL,
    is_update_own_group      BOOLEAN NOT NULL,
    is_update_any_group      BOOLEAN NOT NULL,
    is_delete_own_group      BOOLEAN NOT NULL,
    is_delete_any_group      BOOLEAN NOT NULL,
    role_system_name      VARCHAR(255) REFERENCES roles (system_name)
);