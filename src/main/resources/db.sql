CREATE TABLE Book
(
    id     SERIAL PRIMARY KEY,
    title  VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn   VARCHAR(13)  NOT NULL
);

CREATE TABLE Client
(
    id            SERIAL PRIMARY KEY,
    full_name     VARCHAR(255) NOT NULL,
    date_of_birth DATE         NOT NULL
);

CREATE TABLE BookLoan
(
    id        SERIAL PRIMARY KEY,
    book_id   INT REFERENCES Book (id),
    client_id INT REFERENCES Client (id),
    loan_date DATE NOT NULL
);