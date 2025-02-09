package com.metaway.model;

import com.metaway.model.enums.Perfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "usuario" , schema = "petshop")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String cpf;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    private String senha;
}
