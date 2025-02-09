package com.metaway.model;

import com.metaway.model.enums.TipoContato;
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
@Table(name = "contato" , schema = "petshop")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    private String tag;

    private TipoContato tipoContato;

    private String valor;
}
