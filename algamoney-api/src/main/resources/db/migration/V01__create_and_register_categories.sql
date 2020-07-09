CREATE TABLE category (
    id BIGINT(20) PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS category_sequence START WITH 1 INCREMENT BY 1 MINVALUE 1 CACHE 1;

INSERT INTO category (id, name) values ((VALUES NEXT VALUE FOR category_sequence), 'Lazer');
INSERT INTO category (id, name) values ((VALUES NEXT VALUE FOR category_sequence), 'Alimentação');
INSERT INTO category (id, name) values ((VALUES NEXT VALUE FOR category_sequence), 'Supermercado');
INSERT INTO category (id, name) values ((VALUES NEXT VALUE FOR category_sequence), 'Farmárcia');
INSERT INTO category (id, name) values ((VALUES NEXT VALUE FOR category_sequence), 'Outros');
