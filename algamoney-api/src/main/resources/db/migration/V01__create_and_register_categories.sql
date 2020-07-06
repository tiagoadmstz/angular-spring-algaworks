CREATE TABLE category (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

INSERT INTO category (name) values ('Lazer');
INSERT INTO category (name) values ('Alimentação');
INSERT INTO category (name) values ('Supermercado');
INSERT INTO category (name) values ('Farmárcia');
INSERT INTO category (name) values ('Outros');
