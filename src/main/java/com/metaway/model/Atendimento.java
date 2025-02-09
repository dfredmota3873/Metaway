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
@Table(name = "atendimento" , schema = "metaway")
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
}
