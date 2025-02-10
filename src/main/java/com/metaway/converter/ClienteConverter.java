package com.metaway.converter;

import com.metaway.api.dto.request.ClienteRequest;
import com.metaway.api.dto.response.ClienteResponse;
import com.metaway.model.Cliente;
import com.metaway.utils.Utils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteConverter {

    public Cliente toModel(ClienteRequest request){

        return Cliente.builder()
                .cpf(Utils.removeMascaraCpf(request.getCpf()))
                .nome(request.getNome())
                .build();
    }

    public ClienteResponse toResponse(Cliente cliente){
        return ClienteResponse.builder()
                .id(cliente.getId())
                .cpf(Utils.colocarMascaraNoCpf(cliente.getCpf()))
                .nome(cliente.getNome())
                .dataCadastro(cliente.getDataCadastro())
                .build();
    }

    public List<ClienteResponse> ToListResponse(List<Cliente> listEntity){
        return listEntity.stream().map(this::toResponse).toList();
    }
}
