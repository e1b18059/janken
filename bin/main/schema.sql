CREATE TABLE users (
    id IDENTITY,
    name CHAR NOT NULL
);
CREATE TABLE matches (
    id INT,
    user_1 INT,
    user_2 INT,
    user_1_hand CHAR,
    user_2_hand CHAR
);
