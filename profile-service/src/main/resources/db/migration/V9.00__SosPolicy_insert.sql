INSERT INTO sos_policies (read_sos_list,
                          read_full_sos_list,
                          create_sos,
                          update_own_sos,
                          update_any_sos,
                          delete_own_sos,
                          delete_any_sos,
                          role_system_name)
VALUES (TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, 'ROLE_ADMIN'),
       (TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, 'ROLE_MODERATOR'),
       (TRUE, TRUE, TRUE, TRUE, FALSE, TRUE, FALSE, 'ROLE_OPERATOR'),
       (TRUE, FALSE, TRUE, TRUE, FALSE, TRUE, FALSE, 'ROLE_USER');