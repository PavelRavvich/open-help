CREATE TABLE IF NOT EXISTS roles
(
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR(255) UNIQUE NOT NULL,
    system_name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS users_roles
(
    user_id BIGINT NOT NULL REFERENCES users (id),
    role_id BIGINT NOT NULL REFERENCES roles (id)
);

CREATE UNIQUE INDEX IF NOT EXISTS ui_user_role ON users_roles (user_id, role_id);