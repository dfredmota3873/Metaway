package com.metaway.api.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequest {

    @Size(min = 14)
    @NotNull(message = "Cpf é obrigatório.")
    private String cpf;

    @NotNull(message = "Nome é obrigatório.")
    private String nome;

    @NotNull(message = "Perfil é obrigatório")
    private String perfil;

    @NotNull(message = "Senha é obrigatória")
    @Size(min = 8)
    private String senha;

}
