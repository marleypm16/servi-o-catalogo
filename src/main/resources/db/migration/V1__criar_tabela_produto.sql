CREATE TABLE produto (
     produtoId UUID PRIMARY KEY,
     nome VARCHAR(255) NOT NULL,
     preco NUMERIC(19, 2) NOT NULL,
     descricao VARCHAR NOT NULL ,
     quantidade INT NOT NULL
);