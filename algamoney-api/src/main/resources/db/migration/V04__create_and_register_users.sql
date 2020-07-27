CREATE TABLE user (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(150) NOT NULL
);

CREATE TABLE permition (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(50) NOT NULL
);

CREATE TABLE user_permition (
    user_id BIGINT(20),
    permition_id BIGINT(20),
    PRIMARY KEY(user_id, permition_id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (permition_id) REFERENCES permition(id)
);

INSERT INTO user (name, email, password) VALUES ('Administrador', 'admin@algamoney.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');

INSERT INTO permition (description) VALUES ('ROLE_REGISTER_CATEGORY');
INSERT INTO permition (description) VALUES ('ROLE_SEARCH_CATEGORY');
INSERT INTO permition (description) VALUES ('ROLE_REGISTER_PERSON');
INSERT INTO permition (description) VALUES ('ROLE_REMOVE_PERSON');
INSERT INTO permition (description) VALUES ('ROLE_SEARCH_PERSON');
INSERT INTO permition (description) VALUES ('ROLE_REGISTER_ENTRY');
INSERT INTO permition (description) VALUES ('ROLE_UPDATE_ENTRY');
INSERT INTO permition (description) VALUES ('ROLE_REMOVE_ENTRY');
INSERT INTO permition (description) VALUES ('ROLE_SEARCH_ENTRY');

INSERT INTO user_permition (user_id, permition_id) VALUES (1, 1);
INSERT INTO user_permition (user_id, permition_id) VALUES (1, 2);
INSERT INTO user_permition (user_id, permition_id) VALUES (1, 3);
INSERT INTO user_permition (user_id, permition_id) VALUES (1, 4);
INSERT INTO user_permition (user_id, permition_id) VALUES (1, 5);
INSERT INTO user_permition (user_id, permition_id) VALUES (1, 6);
INSERT INTO user_permition (user_id, permition_id) VALUES (1, 7);
INSERT INTO user_permition (user_id, permition_id) VALUES (1, 8);
INSERT INTO user_permition (user_id, permition_id) VALUES (1, 9);
