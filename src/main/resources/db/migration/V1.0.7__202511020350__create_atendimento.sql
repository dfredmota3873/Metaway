CREATE TABLE metaway.atendimento (  id                   uuid NOT NULL,
                                    descricao            varchar(200) NOT NULL,
                                    data                 timestamp NOT NULL,
                                    id_pet               uuid NOT NULL,
                                    valor                numeric(19,2) NOT NULL,
                                    CONSTRAINT atendimento_pkey PRIMARY KEY (id));

ALTER TABLE metaway.atendimento
    ADD CONSTRAINT fk_atendimento_pet FOREIGN KEY (id_pet)
        REFERENCES metaway.pets (id);
