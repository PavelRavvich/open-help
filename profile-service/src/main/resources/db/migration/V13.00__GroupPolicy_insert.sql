INSERT INTO group_policies (read_group_list,
                            read_any_group_detail,
                            send_group_request,
                            send_group_invite,
                            create_group,
                            update_own_group,
                            update_any_group,
                            delete_own_group,
                            delete_any_group,
                            role_system_name)
VALUES (TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, 'ROLE_ADMIN'),
       (TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, 'ROLE_MODERATOR'),
       (TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, FALSE, FALSE, FALSE, 'ROLE_OPERATOR'),
       (TRUE, FALSE, TRUE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, 'ROLE_USER');