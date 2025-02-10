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
public class EnderecoResponse {

    private UUID id;

    private ClienteResponse cliente;

    private String logradouro;

    private String cidade;

    private String bairro;

    private String complemento;

    private String tag;
}
