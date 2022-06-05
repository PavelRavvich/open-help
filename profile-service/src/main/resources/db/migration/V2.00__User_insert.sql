INSERT INTO users (id, nickname, username, password, activation_code)
VALUES (1, 'admin-nickname', 'admin', '$2a$10$NHHGm4Cx8sMwi054741uUuk4v5MiGgatPTla9Onzewy7PWCCrRAOK',
        '956226af-10ee-49dc-a0c1-6d74a5cc0fdf');
SELECT setval('users_id_seq', 3);