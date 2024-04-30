CREATE TABLE roles
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    created_at       datetime NULL,
    last_modified_at datetime NULL,
    deleted          BIT(1) NOT NULL DEFAULT false,
    name             VARCHAR(255) NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE tokens
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    created_at       datetime NULL,
    last_modified_at datetime NULL,
    deleted          BIT(1) NOT NULL default false,
    value            VARCHAR(255) NULL,
    users_id         BIGINT NULL,
    expiry_at        datetime NULL,
    CONSTRAINT pk_tokens PRIMARY KEY (id)
);

CREATE TABLE users
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    created_at       datetime NULL,
    last_modified_at datetime NULL,
    deleted          BIT(1) NOT NULL default false,
    name             VARCHAR(255) NULL,
    email            VARCHAR(255) NULL,
    password         VARCHAR(255) NULL,
    verified         BIT(1) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE users_roles
(
    users_id BIGINT NOT NULL,
    roles_id BIGINT NOT NULL
);

ALTER TABLE tokens
    ADD CONSTRAINT FK_TOKENS_ON_USERS FOREIGN KEY (users_id) REFERENCES users (id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_roles FOREIGN KEY (roles_id) REFERENCES roles (id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_users FOREIGN KEY (users_id) REFERENCES users (id);