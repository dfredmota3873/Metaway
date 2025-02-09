package com.metaway.api.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteRequest {

    @Size(min = 14)
    @NotNull(message = "Cpf é obrigatório.")
    private String cpf;

    @NotNull(message = "Nome é obrigatório.")
    private String nome;
}
