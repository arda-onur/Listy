CREATE TABLE IF NOT EXISTS listy.comment
(
    id         BIGSERIAL PRIMARY KEY ,
    author     VARCHAR(255) NOT NULL,
    comment    VARCHAR(255) NOT NULL,
    date       DATE NOT NULL,
    CONSTRAINT author FOREIGN KEY (author) REFERENCES listy."user"(username)

    );