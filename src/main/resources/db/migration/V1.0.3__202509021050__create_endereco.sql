CREATE TABLE metaway.endereco (     id                   uuid NOT NULL,
                                    id_cliente           uuid NOT NULL,
                                    logradouro           varchar(200) NOT NULL,
                                    cidade               varchar(200) NOT NULL,
                                    bairro               varchar(200) NOT NULL,
                                    complemento          varchar(200) NOT NULL,
                                    tag                  varchar(100) NOT NULL,
                                    CONSTRAINT endereco_pkey PRIMARY KEY (id));


ALTER TABLE metaway.endereco
    ADD CONSTRAINT fk_cliente_endereco FOREIGN KEY (id_cliente)
        REFERENCES metaway.cliente (id);