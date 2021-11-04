CREATE TABLE IF NOT EXISTS document_exception_tb (
    id serial PRIMARY KEY,
    document VARCHAR ( 11 ) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS cashback_tb (
    id serial PRIMARY KEY,
    min_value_cashback NUMERIC(5,2) UNIQUE NOT NULL,
    max_value_cashback NUMERIC(5,2) UNIQUE NOT NULL,
    percentage_cashback integer UNIQUE NOT NULL
);

INSERT INTO document_exception_tb (id, document) VALUES
    (1, '15350946056') ON CONFLICT DO NOTHING;

INSERT INTO cashback_tb (id, min_value_cashback, max_value_cashback, percentage_cashback) VALUES
    (1, 0.00, 1000.00, 10),
    (2, 1000.01, 1500.00, 15),
    (3, 1500.01, 99999.99, 20) ON CONFLICT DO NOTHING;