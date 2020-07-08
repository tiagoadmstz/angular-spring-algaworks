CREATE TABLE person (
    id BIGINT(20) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    street_andress VARCHAR(255) NOT NULL,
    number VARCHAR(20) NOT NULL DEFAULT 'N/A',
    adjunct VARCHAR(60),
    district VARCHAR(60) NOT NULL,
    zip_code VARCHAR(8) NOT NULL,
    city VARCHAR(60) NOT NULL,
    state VARCHAR(60) NOT NULL
);

INSERT INTO person (id, name, active, street_Andress, number, adjunct, district, zip_code, city, state) VALUES (1, 'João Silva', true, 'Rua do Abacaxi', '10', null, 'Brasil', '3840012', 'Uberlândia', 'MG');
INSERT INTO person (id, name, active, street_andress, number, adjunct, district, zip_code, city, state) VALUES (2, 'Maria Rita', true, 'Rua do Sabiá', '110', 'Apto 101', 'Colina', '1140012', 'Ribeirão Preto', 'SP');
INSERT INTO person (id, name, active, street_andress, number, adjunct, district, zip_code, city, state) VALUES (3, 'Pedro Santos', true, 'Rua da Bateria', '23', null, 'Morumbi', '5421212', 'Goiânia', 'GO');
INSERT INTO person (id, name, active, street_andress, number, adjunct, district, zip_code, city, state) VALUES (4, 'Ricardo Pereira', true, 'Rua do Motorista', '123', 'Apto 302', 'Aparecida', '3840012', 'Salvador', 'BA');
INSERT INTO person (id, name, active, street_andress, number, adjunct, district, zip_code, city, state) VALUES (5, 'Josué Mariano', true, 'Av Rio Branco', '321', null, 'Jardins', '5640012', 'Natal', 'RN');
INSERT INTO person (id, name, active, street_andress, number, adjunct, district, zip_code, city, state) VALUES (6, 'Pedro Barbosa', true, 'Av Brasil', '100', null, 'Tubalina', '7740012', 'Porto Alegre', 'RS');
INSERT INTO person (id, name, active, street_andress, number, adjunct, district, zip_code, city, state) VALUES (7, 'Henrique Medeiros', true, 'Rua do Sapo', '1120', 'Apto 201', 'Centro', '1240012', 'Rio de Janeiro', 'RJ');
INSERT INTO person (id, name, active, street_andress, number, adjunct, district, zip_code, city, state) VALUES (8, 'Carlos Santana', true, 'Rua da Manga', '433', null, 'Centro', '3140012', 'Belo Horizonte', 'MG');
INSERT INTO person (id, name, active, street_andress, number, adjunct, district, zip_code, city, state) VALUES (9, 'Leonardo Oliveira', true, 'Rua do Músico', '566', null, 'Segismundo Pereira', '3840000', 'Uberlândia', 'MG');
INSERT INTO person (id, name, active, street_andress, number, adjunct, district, zip_code, city, state) VALUES (10, 'Isabela Martins', true, 'Rua da Terra', '1233', 'Apto 10', 'Vigilato', '9940012', 'Manaus', 'AM');

CREATE SEQUENCE IF NOT EXISTS person_sequence START WITH 11 INCREMENT BY 1 MINVALUE 1;