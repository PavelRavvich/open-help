CREATE TABLE IF NOT EXISTS access_policies
(
    id                          BIGSERIAL PRIMARY KEY,
    is_read_role_list           BOOLEAN NOT NULL,
    is_read_own_role            BOOLEAN NOT NULL,
    is_create_role              BOOLEAN NOT NULL,
    is_update_role              BOOLEAN NOT NULL,
    is_delete_role              BOOLEAN NOT NULL,
    is_read_user_list           BOOLEAN NOT NULL,
    is_read_any_user_details    BOOLEAN NOT NULL,
    is_read_any_user_action_log BOOLEAN NOT NULL,
    is_create_user              BOOLEAN NOT NULL,
    is_update_own_user          BOOLEAN NOT NULL,
    is_update_any_user          BOOLEAN NOT NULL,
    is_delete_own_user          BOOLEAN NOT NULL,
    is_delete_any_user          BOOLEAN NOT NULL,
    is_read_sos_list            BOOLEAN NOT NULL,
    is_read_full_sos_list       BOOLEAN NOT NULL,
    is_read_any_sos_details     BOOLEAN NOT NULL,
    is_create_sos               BOOLEAN NOT NULL,
    is_update_own_sos           BOOLEAN NOT NULL,
    is_update_any_sos           BOOLEAN NOT NULL,
    is_delete_own_sos           BOOLEAN NOT NULL,
    is_delete_any_sos           BOOLEAN NOT NULL,
    is_read_story_list          BOOLEAN NOT NULL,
    is_read_full_story_list     BOOLEAN NOT NULL,
    is_read_any_story_details   BOOLEAN NOT NULL,
    is_create_story             BOOLEAN NOT NULL,
    is_update_own_story         BOOLEAN NOT NULL,
    is_update_any_story         BOOLEAN NOT NULL,
    is_delete_own_story         BOOLEAN NOT NULL,
    is_delete_any_story         BOOLEAN NOT NULL,
    is_read_group_list          BOOLEAN NOT NULL,
    is_read_full_group_list     BOOLEAN NOT NULL,
    is_read_any_group_detail    BOOLEAN NOT NULL,
    is_send_group_request       BOOLEAN NOT NULL,
    is_send_group_invite        BOOLEAN NOT NULL,
    is_create_group             BOOLEAN NOT NULL,
    is_update_own_group         BOOLEAN NOT NULL,
    is_update_any_group         BOOLEAN NOT NULL,
    is_delete_own_group         BOOLEAN NOT NULL,
    is_delete_any_group         BOOLEAN NOT NULL,
    role_id                     BIGINT REFERENCES roles (id)
);