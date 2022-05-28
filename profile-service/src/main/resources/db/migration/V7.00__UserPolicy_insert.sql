INSERT INTO user_policies (read_user_list,
                           read_any_user_details,
                           read_any_user_action_log,
                           create_user,
                           update_own_user,
                           update_any_user,
                           delete_any_user,
                           role_system_name)
VALUES (TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, 'ROLE_ADMIN'),
       (TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, FALSE, 'ROLE_MODERATOR'),
       (TRUE, TRUE, TRUE, FALSE, TRUE, FALSE, FALSE, 'ROLE_OPERATOR'),
       (FALSE, FALSE, FALSE, FALSE, TRUE, FALSE, FALSE, 'ROLE_USER');