CREATE SCHEMA IF NOT EXISTS management_db_test;

CREATE TABLE IF NOT EXISTS management_db_test.USER(
    id SERIAL   PRIMARY KEY,
    username    VARCHAR(255),
    password    VARCHAR(255),
    role        VARCHAR(255)
);

INSERT INTO management_db_test.USER(username, password, role)
VALUES('user1', 'password1', 'role_admin'),
('user2', 'password2', 'role_user');