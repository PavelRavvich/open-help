CREATE TABLE IF NOT EXISTS group_policies
(
    read_group_list       BOOLEAN NOT NULL,
    read_any_group_detail BOOLEAN NOT NULL,
    send_group_request    BOOLEAN NOT NULL,
    send_group_invite     BOOLEAN NOT NULL,
    create_group          BOOLEAN NOT NULL,
    update_own_group      BOOLEAN NOT NULL,
    update_any_group      BOOLEAN NOT NULL,
    delete_own_group      BOOLEAN NOT NULL,
    delete_any_group      BOOLEAN NOT NULL,
    role_system_name      VARCHAR(255) REFERENCES roles (system_name)
);