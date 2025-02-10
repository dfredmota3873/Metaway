package com.metaway.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoRequest {

    private UUID cliente;

    @NotNull(message = "Logradouro é obrigatório.")
    private String logradouro;

    @NotNull(message = "Cidade é obrigatório")
    private String cidade;

    @NotNull(message = "Bairro é obrigatório")
    private String bairro;

    private String complemento;

    private String tag;
}
