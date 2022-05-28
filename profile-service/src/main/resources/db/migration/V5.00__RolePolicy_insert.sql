INSERT INTO role_policies (read_role_list,
                           create_role,
                           update_role,
                           delete_role,
                           role_system_name)
VALUES (TRUE, TRUE, TRUE, TRUE, 'ROLE_ADMIN'),
       (FALSE, FALSE, FALSE, FALSE, 'ROLE_MODERATOR'),
       (FALSE, FALSE, FALSE, FALSE, 'ROLE_OPERATOR'),
       (FALSE, FALSE, FALSE, FALSE, 'ROLE_USER');