CREATE TABLE accounts
(
    id BINARY (16) PRIMARY KEY,
    name             VARCHAR(30)  NOT NULL,
    last_name        VARCHAR(30)  NOT NULL,
    email            VARCHAR(100) NOT NULL UNIQUE,
    password         TEXT         NOT NULL,
    account_enabling BOOLEAN
);

CREATE TABLE roles
(
    id   SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    role VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE role_accounts
(
    account_id BINARY (16) NOT NULL,
    role_id SMALLINT UNSIGNED NOT NULL,
    PRIMARY KEY (account_id, role_id),
    FOREIGN KEY (account_id) REFERENCES accounts (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE tokens
(
    id BINARY (16) PRIMARY KEY,
    token            VARCHAR(100) NOT NULL,
    time_of_creation TIMESTAMP,
    lifetime         TIMESTAMP,
    operation_type   VARCHAR(50)  NOT NULL,
    account_id BINARY (16),
    FOREIGN KEY (account_id) REFERENCES accounts (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX token_index ON tokens (token);
CREATE UNIQUE INDEX email_index ON accounts (id, email);

INSERT INTO roles (role)
VALUES ('ADMIN');
