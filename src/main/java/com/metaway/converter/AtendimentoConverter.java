package com.metaway.converter;

import com.metaway.api.dto.request.AtendimentoRequest;
import com.metaway.api.dto.response.AtendimentoResponse;
import com.metaway.model.Atendimento;
import com.metaway.model.Pet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AtendimentoConverter {

    private final PetConverter petConverter;

    public Atendimento toModel(AtendimentoRequest atendimentoRequest) {

        return Atendimento.builder()
                .pet(Pet.builder()
                        .id(atendimentoRequest.getPet())
                        .build())
                .data(atendimentoRequest.getData())
                .valor(atendimentoRequest.getValor())
                .descricao(atendimentoRequest.getDescricao())
                .build();
    }

    public AtendimentoResponse toResponse(Atendimento atendimento) {

        return AtendimentoResponse.builder()
                .id(atendimento.getId())
                .pet(petConverter.toResponse(atendimento.getPet()))
                .data(atendimento.getData())
                .valor(atendimento.getValor())
                .descricao(atendimento.getDescricao())
                .build();

    }

    public List<AtendimentoResponse> ToListResponse(List<Atendimento> listEntity){
        return listEntity.stream().map(this::toResponse).toList();
    }
}
