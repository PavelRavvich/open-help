INSERT INTO story_policies (read_story_list,
                            read_full_story_list,
                            create_story,
                            update_own_story,
                            update_any_story,
                            delete_own_story,
                            delete_any_story,
                            role_system_name)
VALUES (TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, 'ROLE_ADMIN'),
       (TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, 'ROLE_MODERATOR'),
       (TRUE, TRUE, TRUE, TRUE, FALSE, TRUE, FALSE, 'ROLE_OPERATOR'),
       (TRUE, FALSE, TRUE, TRUE, FALSE, TRUE, FALSE, 'ROLE_USER');