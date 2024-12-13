CREATE TABLE user_product (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              account_number VARCHAR(255) UNIQUE NOT NULL,
                              balance DECIMAL(19,2) NOT NULL,
                              product_type VARCHAR(255) NOT NULL
);

CREATE TABLE payment (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         amount DECIMAL(19,2) NOT NULL,
                         product_id BIGINT NOT NULL,
                         status VARCHAR(255) NOT NULL
);
