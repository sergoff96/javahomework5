CREATE TABLE limits
(
    id              BIGSERIAL PRIMARY KEY,
    user_id         BIGINT UNIQUE  NOT NULL,
    daily_limit     NUMERIC(15, 2) NOT NULL,
    remaining_limit NUMERIC(15, 2) NOT NULL
);
