CREATE TABLE payment
(
    id         BIGSERIAL PRIMARY KEY,
    amount     NUMERIC(15, 2) NOT NULL,
    product_id BIGINT         NOT NULL,
    status     VARCHAR(50)    NOT NULL
);
