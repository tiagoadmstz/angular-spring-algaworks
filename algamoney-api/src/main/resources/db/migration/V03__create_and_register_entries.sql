CREATE TABLE entry (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(50) NOT NULL,
    due_date DATE,
    payday DATE,
    entry_value DECIMAL(10,2) NOT NULL,
    note VARCHAR(100),
    type VARCHAR(20) NOT NULL,
    category_id BIGINT(20) NOT NULL,
    person_id BIGINT(20) NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category(id),
    FOREIGN KEY (person_id) REFERENCES person(id)
);

INSERT INTO entry(description, due_date, payday, entry_value, note, type, category_id, person_id) VALUES ('Compra de mouse', '2020-07-09', '2020-07-09', 15.00, 'teste 1', 'EXPENSE', 5, 1);
INSERT INTO entry(description, due_date, payday, entry_value, note, type, category_id, person_id) VALUES ('Venda de mouse', '2020-07-09', '2020-07-09', 15.00, 'teste 2', 'INCOME', 5, 1);
