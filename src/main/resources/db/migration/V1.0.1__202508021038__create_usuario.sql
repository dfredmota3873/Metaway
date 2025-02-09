CREATE TABLE metaway.conta (           id                   uuid NOT NULL,
                                    cpf                  varchar(12) NOT NULL,
                                    nome                 varchar(100) NOT NULL,
                                    perfil               varchar(100) NOT NULL,
                                    data_cadastro        timestamp NOT NULL ,
                                    CONSTRAINT usuario_pkey PRIMARY KEY (id));