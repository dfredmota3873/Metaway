package com.metaway.model;

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
@Table(name = "raca" , schema = "petshop")
public class Raca {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String descricao;
}
