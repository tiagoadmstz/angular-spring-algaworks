CREATE TABLE category (
    id BIGINT(20) PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

INSERT INTO category (id, name) values (1, 'Lazer');
INSERT INTO category (id, name) values (2, 'Alimentação');
INSERT INTO category (id, name) values (3, 'Supermercado');
INSERT INTO category (id, name) values (4, 'Farmárcia');
INSERT INTO category (id, name) values (5, 'Outros');

CREATE SEQUENCE IF NOT EXISTS category_sequence START WITH 6 INCREMENT BY 1 MINVALUE 1;