package com.metaway.api.dto.response;

import com.metaway.model.enums.TipoContato;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ContatoResponse {

    private UUID id;

    private ClienteResponse cliente;

    private String tag;

    private TipoContato tipoContato;

    private String valor;
}
