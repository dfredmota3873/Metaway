package com.metaway.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "cliente" , schema = "petshop")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;

    private String cpf;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;
}
