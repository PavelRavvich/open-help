CREATE TABLE IF NOT EXISTS story_policies
(
    id                   BIGSERIAL PRIMARY KEY,
    is_read_story_list      BOOLEAN NOT NULL,
    is_read_full_story_list BOOLEAN NOT NULL,
    is_create_story         BOOLEAN NOT NULL,
    is_update_own_story     BOOLEAN NOT NULL,
    is_update_any_story     BOOLEAN NOT NULL,
    is_delete_own_story     BOOLEAN NOT NULL,
    is_delete_any_story     BOOLEAN NOT NULL,
    role_system_name     VARCHAR(255) REFERENCES roles (system_name)
);