-- liquibase formatted sql

-- changeset dh:1
CREATE TABLE IF NOT EXISTS user (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS task (
    id VARCHAR(36) PRIMARY KEY,
    user_id VARCHAR(36),
    title VARCHAR(255),
    description LONGTEXT,
    importance VARCHAR(36),
    CONSTRAINT fk_task_user_id FOREIGN KEY (user_id) REFERENCES user(id)
);
