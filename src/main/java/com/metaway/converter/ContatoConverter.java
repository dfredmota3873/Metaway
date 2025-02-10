package com.metaway.converter;

import com.metaway.api.dto.request.ContatoRequest;
import com.metaway.api.dto.response.ContatoResponse;
import com.metaway.model.Cliente;
import com.metaway.model.Contato;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContatoConverter {

    private final ClienteConverter clienteConverter;

    public Contato toModel(ContatoRequest request) {

        return Contato.builder()
                        .cliente(Cliente.builder()
                        .id(request.getCliente()).build())
                        .valor(request.getValor())
                        .tipoContato(request.getTipoContato())
                        .tag(request.getTag())
                        .build();
    }

    public ContatoResponse toResponse(Contato contato) {

        return ContatoResponse.builder()
                .id(contato.getId())
                .cliente(clienteConverter.toResponse(contato.getCliente()))
                .id(contato.getId())
                .valor(contato.getValor())
                .tipoContato(contato.getTipoContato())
                .tag(contato.getTag())
                .build();

    }

    public List<ContatoResponse> ToListResponse(List<Contato> listEntity){
        return listEntity.stream().map(this::toResponse).toList();
    }

}
