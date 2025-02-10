package com.metaway.converter;

import com.metaway.api.dto.request.EnderecoRequest;
import com.metaway.api.dto.response.EnderecoResponse;
import com.metaway.api.dto.response.UsuarioResponse;
import com.metaway.model.Cliente;
import com.metaway.model.Endereco;
import com.metaway.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EnderecoConverter {

    private final ClienteConverter clienteConverter;

    public Endereco toModel(EnderecoRequest request) {

        return Endereco.builder()
                        .cliente(Cliente.builder()
                        .id(request.getCliente()).build())
                        .logradouro(request.getLogradouro())
                        .bairro(request.getBairro())
                        .cidade(request.getCidade())
                        .complemento(request.getComplemento())
                        .tag(request.getTag())
                        .build();
    }

    public EnderecoResponse toResponse(Endereco endereco) {

        return EnderecoResponse.builder()
                .id(endereco.getId())
                .cliente(clienteConverter.toResponse(endereco.getCliente()))
                .logradouro(endereco.getLogradouro())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .tag(endereco.getTag())
                .build();

    }

    public List<EnderecoResponse> ToListResponse(List<Endereco> listEntity){
        return listEntity.stream().map(this::toResponse).toList();
    }

}
