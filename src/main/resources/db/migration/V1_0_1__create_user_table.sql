CREATE TABLE IF NOT EXISTS listy.user
(
    id                      BIGSERIAL PRIMARY KEY,
    username                VARCHAR(255) NOT NULL UNIQUE ,
    password                VARCHAR(255) NOT NULL,
    role                    VARCHAR(255) NOT NULL
);