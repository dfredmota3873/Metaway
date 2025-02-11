CREATE TABLE metaway.pets (         id                   uuid NOT NULL,
                                    nome                 varchar(200) NOT NULL,
                                    data_nascimento      timestamp NOT NULL,
                                    id_cliente           uuid NOT NULL,
                                    id_raca              uuid NOT NULL,
                                    CONSTRAINT pets_pkey PRIMARY KEY (id));


ALTER TABLE metaway.pets
    ADD CONSTRAINT fk_cliente_pet FOREIGN KEY (id_cliente)
        REFERENCES metaway.cliente (id);


ALTER TABLE metaway.pets
    ADD CONSTRAINT fk_raca_pets FOREIGN KEY (id_raca)
        REFERENCES metaway.raca (id);
