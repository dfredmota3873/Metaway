package com.metaway.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UsuarioResponse {

    private UUID id;
    private String nome;
    private String cpf;
    private String perfil;
    private String senha;
}
