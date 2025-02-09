CREATE TABLE metaway.usuario (           id                   uuid NOT NULL,
                                    cpf                  varchar(12) NOT NULL,
                                    nome                 varchar(100) NOT NULL,
                                    perfil               varchar(100) NOT NULL,
                                    senha                varchar(240) NOT NULL,
                                    CONSTRAINT usuario_pkey PRIMARY KEY (id));