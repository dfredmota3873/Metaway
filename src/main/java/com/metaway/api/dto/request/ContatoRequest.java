package com.metaway.api.dto.request;

import com.metaway.model.enums.TipoContato;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContatoRequest {

    private UUID cliente;

    private String tag;

    private TipoContato tipoContato;

    private String valor;
}
