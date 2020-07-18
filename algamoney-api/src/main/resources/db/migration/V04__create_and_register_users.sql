CREATE TABLE user (
    id BIGINT(20) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(150) NOT NULL
);

CREATE TABLE permition (
    id BIGINT(20) PRIMARY KEY,
    description VARCHAR(50) NOT NULL
);

CREATE TABLE user_permition (
    user_id BIGINT(20),
    permition_id BIGINT(20),
    PRIMARY KEY(user_id, permition_id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (permition_id) REFERENCES permition(id)
);

CREATE SEQUENCE user_sequence START WITH 1 INCREMENT BY 1 MINVALUE 1 CACHE 1;
CREATE SEQUENCE permition_sequence START WITH 1 INCREMENT BY 1 MINVALUE 1 CACHE 1;

INSERT INTO user (id, name, email, password) VALUES ((VALUES NEXT VALUE FOR user_sequence), 'Administrador', 'admin@algamoney.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');

--admin
INSERT INTO permition (id, description) VALUES ((VALUES NEXT VALUE FOR permition_sequence), 'ROLE_REGISTER_CATEGORY');
INSERT INTO permition (id, description) VALUES ((VALUES NEXT VALUE FOR permition_sequence), 'ROLE_SEARCH_CATEGORY');
INSERT INTO permition (id, description) VALUES ((VALUES NEXT VALUE FOR permition_sequence), 'ROLE_REGISTER_PERSON');
INSERT INTO permition (id, description) VALUES ((VALUES NEXT VALUE FOR permition_sequence), 'ROLE_REMOVE_PERSON');
INSERT INTO permition (id, description) VALUES ((VALUES NEXT VALUE FOR permition_sequence), 'ROLE_SEARCH_PERSON');
INSERT INTO permition (id, description) VALUES ((VALUES NEXT VALUE FOR permition_sequence), 'ROLE_REGISTER_ENTRY');
INSERT INTO permition (id, description) VALUES ((VALUES NEXT VALUE FOR permition_sequence), 'ROLE_REMOVE_ENTRY');
INSERT INTO permition (id, description) VALUES ((VALUES NEXT VALUE FOR permition_sequence), 'ROLE_SEARCH_ENTRY');

INSERT INTO user_permition (user_id, permition_id) VALUES (1, 1);
INSERT INTO user_permition (user_id, permition_id) VALUES (1, 2);
INSERT INTO user_permition (user_id, permition_id) VALUES (1, 3);
INSERT INTO user_permition (user_id, permition_id) VALUES (1, 4);
INSERT INTO user_permition (user_id, permition_id) VALUES (1, 5);
INSERT INTO user_permition (user_id, permition_id) VALUES (1, 6);
INSERT INTO user_permition (user_id, permition_id) VALUES (1, 7);
INSERT INTO user_permition (user_id, permition_id) VALUES (1, 8);
