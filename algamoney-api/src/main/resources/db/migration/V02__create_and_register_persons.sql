CREATE TABLE person (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
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

INSERT INTO person (name, active, street_Andress, number, adjunct, district, zip_code, city, state) VALUES ('João Silva', true, 'Rua do Abacaxi', '10', null, 'Brasil', '38400120', 'Uberlândia', 'MG');
INSERT INTO person (name, active, street_andress, number, adjunct, district, zip_code, city, state) VALUES ('Maria Rita', true, 'Rua do Sabiá', '110', 'Apto 101', 'Colina', '11400120', 'Ribeirão Preto', 'SP');
INSERT INTO person (name, active, street_andress, number, adjunct, district, zip_code, city, state) VALUES ('Pedro Santos', true, 'Rua da Bateria', '23', null, 'Morumbi', '54212120', 'Goiânia', 'GO');
INSERT INTO person (name, active, street_andress, number, adjunct, district, zip_code, city, state) VALUES ('Ricardo Pereira', true, 'Rua do Motorista', '123', 'Apto 302', 'Aparecida', '3840012', 'Salvador', 'BA');
INSERT INTO person (name, active, street_andress, number, adjunct, district, zip_code, city, state) VALUES ('Josué Mariano', true, 'Av Rio Branco', '321', null, 'Jardins', '56400120', 'Natal', 'RN');
INSERT INTO person (name, active, street_andress, number, adjunct, district, zip_code, city, state) VALUES ('Pedro Barbosa', true, 'Av Brasil', '100', null, 'Tubalina', '77400120', 'Porto Alegre', 'RS');
INSERT INTO person (name, active, street_andress, number, adjunct, district, zip_code, city, state) VALUES ('Henrique Medeiros', true, 'Rua do Sapo', '1120', 'Apto 201', 'Centro', '12400120', 'Rio de Janeiro', 'RJ');
INSERT INTO person (name, active, street_andress, number, adjunct, district, zip_code, city, state) VALUES ('Carlos Santana', true, 'Rua da Manga', '433', null, 'Centro', '31400120', 'Belo Horizonte', 'MG');
INSERT INTO person (name, active, street_andress, number, adjunct, district, zip_code, city, state) VALUES ('Leonardo Oliveira', true, 'Rua do Músico', '566', null, 'Segismundo Pereira', '38400000', 'Uberlândia', 'MG');
INSERT INTO person (name, active, street_andress, number, adjunct, district, zip_code, city, state) VALUES ('Isabela Martins', true, 'Rua da Terra', '1233', 'Apto 10', 'Vigilato', '99400120', 'Manaus', 'AM');

