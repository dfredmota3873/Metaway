package com.metaway.converter;

import com.metaway.api.dto.request.ContatoRequest;
import com.metaway.api.dto.request.RacaRequest;
import com.metaway.api.dto.response.ContatoResponse;
import com.metaway.api.dto.response.RacaResponse;
import com.metaway.model.Cliente;
import com.metaway.model.Contato;
import com.metaway.model.Raca;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RacaConverter {

    public Raca toModel(RacaRequest request) {

        return Raca.builder()
                        .descricao(request.getDescricao())
                        .build();
    }

    public RacaResponse toResponse(Raca raca) {

        return RacaResponse.builder()
                .id(raca.getId())
                .descricao(raca.getDescricao())
                .build();

    }

    public List<RacaResponse> ToListResponse(List<Raca> listEntity){
        return listEntity.stream().map(this::toResponse).toList();
    }

}
