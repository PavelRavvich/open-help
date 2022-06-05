CREATE TABLE IF NOT EXISTS users
(
    id                      BIGSERIAL PRIMARY KEY,
    nickname                VARCHAR(255) UNIQUE NOT NULL,
    username                VARCHAR(255) UNIQUE NOT NULL,
    password                VARCHAR(255)        NOT NULL,
    reputation              INT     DEFAULT 0,
    account_non_expired     BOOLEAN DEFAULT TRUE,
    account_non_locked      BOOLEAN DEFAULT TRUE,
    credentials_non_expired BOOLEAN DEFAULT TRUE,
    enabled                 BOOLEAN DEFAULT TRUE,
    activation_code         UUID
);

