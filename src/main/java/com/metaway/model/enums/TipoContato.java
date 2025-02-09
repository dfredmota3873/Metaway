package com.metaway.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoContato {

    TELEFONE("Telefone"),
    EMAIL("Email");

    private String descricao;
}
