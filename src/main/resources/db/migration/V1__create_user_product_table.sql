CREATE TABLE user_product
(
    id             BIGSERIAL PRIMARY KEY,
    account_number VARCHAR(50) UNIQUE NOT NULL,
    balance        NUMERIC(15, 2)     NOT NULL,
    product_type   VARCHAR(50)        NOT NULL
);
