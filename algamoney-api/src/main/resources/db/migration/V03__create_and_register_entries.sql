CREATE TABLE registry (
    id BIGINT(20) PRIMARY KEY,
    description VARCHAR(50) NOT NULL,
    due_date DATE,
    payday DATE,
    registry_value DECIMAL(10,2) NOT NULL,
    note VARCHAR(100),
    type VARCHAR(20) NOT NULL,
    category_id BIGINT(20) NOT NULL,
    person_id BIGINT(20) NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category(id),
    FOREIGN KEY (person_id) REFERENCES person(id)
);

CREATE SEQUENCE registry_sequence START WITH 1 INCREMENT BY 1 MINVALUE 1 CACHE 1;

INSERT INTO registry(id, description, due_date, payday, registry_value, note, type, category_id, person_id) VALUES ((VALUES NEXT VALUE FOR registry_sequence), 'Compra de mouse', '2020-07-09', '2020-07-09', 15.00, 'teste 1', 'EXPENSE', 5, 1);
INSERT INTO registry(id, description, due_date, payday, registry_value, note, type, category_id, person_id) VALUES ((VALUES NEXT VALUE FOR registry_sequence), 'Venda de mouse', '2020-07-09', '2020-07-09', 15.00, 'teste 2', 'INCOME', 5, 1);
