CREATE TABLE IF NOT EXISTS BOOKS (
                                         BOOK_ID INTEGER PRIMARY KEY,
                                         TITLE VARCHAR(250),
                                         PRICE double
                                         );

CREATE TABLE IF NOT EXISTS AUTHORS (
                                     AUTHOR_ID INTEGER PRIMARY KEY,
                                     AUTHOR_NAME VARCHAR(250),
                                     PRICE double);

CREATE TABLE IF NOT EXISTS BOOK_AUTHOR (
                                       AUTHOR_ID INTEGER,
                                       BOOK_ID INTEGER,
                                       PRIMARY KEY(AUTHOR_ID, BOOK_ID),
                                       FOREIGN KEY(AUTHOR_ID) REFERENCES AUTHORS(AUTHOR_ID),
                                       FOREIGN KEY(BOOK_ID ) REFERENCES BOOKS(BOOK_ID)
);