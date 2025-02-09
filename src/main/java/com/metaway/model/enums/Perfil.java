package com.metaway.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Perfil {

    CLIENTE("Cliente"),
    ADMIN("Administrador");

    private String descricao;
}
