CREATE TABLE metaway.contato (      id                   uuid NOT NULL,
                                    id_cliente           uuid NOT NULL,
                                    tipo                 varchar(200) NOT NULL,
                                    valor                varchar(200) NOT NULL,
                                    tag                  varchar(100) NOT NULL,
                                    CONSTRAINT contato_pkey PRIMARY KEY (id));


ALTER TABLE metaway.contato
    ADD CONSTRAINT fk_cliente_contato FOREIGN KEY (id_cliente)
        REFERENCES metaway.cliente (id);