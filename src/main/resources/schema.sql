CREATE TABLE user_product
(
    id             BIGSERIAL PRIMARY KEY,
    account_number VARCHAR(255) UNIQUE NOT NULL,
    balance        NUMERIC(19, 2)      NOT NULL,
    product_type   VARCHAR(50)         NOT NULL
);

INSERT INTO user_product (account_number, balance, product_type)
VALUES ('123456789', 1000.00, 'account'),
       ('987654321', 5000.00, 'card');