CREATE TABLE users (
     user_id UUID PRIMARY KEY,
     username VARCHAR(255) NOT NULL UNIQUE ,
     email VARCHAR(255) NOT NULL UNIQUE ,
     password VARCHAR NOT NULL ,
     role VARCHAR(20) NOT NULL
);