CREATE TABLE IF NOT EXISTS story_policies
(
    read_story_list      BOOLEAN NOT NULL,
    read_full_story_list BOOLEAN NOT NULL,
    create_story         BOOLEAN NOT NULL,
    update_own_story     BOOLEAN NOT NULL,
    update_any_story     BOOLEAN NOT NULL,
    delete_own_story     BOOLEAN NOT NULL,
    delete_any_story     BOOLEAN NOT NULL,
    role_system_name     VARCHAR(255) REFERENCES roles (system_name)
);