package com.metaway.api.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequest {

    private String cpf;
    private String nome;
    private String perfil;
    private String senha;

}
