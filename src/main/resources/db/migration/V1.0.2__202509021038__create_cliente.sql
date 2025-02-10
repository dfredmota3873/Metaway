CREATE TABLE metaway.cliente (      id                   uuid NOT NULL,
                                    cpf                  varchar(12) NOT NULL,
                                    nome                 varchar(100) NOT NULL,
                                    data_cadastro        timestamp NOT NULL,
                                    CONSTRAINT cliente_pkey PRIMARY KEY (id));