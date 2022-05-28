INSERT INTO roles (id, title, system_name)
VALUES (1, 'User (Default)', 'ROLE_USER'),
       (2, 'Operator', 'ROLE_OPERATOR'),
       (3, 'Moderator', 'ROLE_MODERATOR'),
       (4, 'Admin', 'ROLE_ADMIN');
SELECT setval('roles_id_seq', 3);
