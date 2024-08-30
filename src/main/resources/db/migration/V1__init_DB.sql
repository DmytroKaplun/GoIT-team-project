CREATE SEQUENCE IF NOT EXISTS seq_url_id
    START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS seq_user_id
    START WITH 1 INCREMENT BY 1;

CREATE TABLE users
(
    id BIGINT DEFAULT nextval('seq_user_id'),
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT pk_users_id PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS urls
(
    id                 BIGINT DEFAULT nextval('seq_url_id'),
    native_link        TEXT NOT NULL,
    short_link         VARCHAR(255) NOT NULL,
    transactions_count BIGINT,
    user_id            BIGINT,
    created_at         timestamp,
    expired_time       timestamp,
    CONSTRAINT url_pk PRIMARY KEY (id),
    CONSTRAINT url_fk FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS roles
(
    id BIGSERIAL,
    name VARCHAR(50) UNIQUE NOT NULL,
    CONSTRAINT pk_roles_id PRIMARY KEY(id)

);

CREATE TABLE IF NOT EXISTS users_roles
(
    user_id BIGINT,
    role_id BIGINT,
    CONSTRAINT pk_users_roles PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_users_roles_user_id FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_users_roles_role_id FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE INDEX idx_short_code ON urls(short_link);
CREATE INDEX idx_user_id ON urls(user_id);
CREATE INDEX idx_users_roles_user ON users_roles(user_id);
CREATE INDEX idx_users_roles_role ON users_roles(role_id);

INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');