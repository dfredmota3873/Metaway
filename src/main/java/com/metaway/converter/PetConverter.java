package com.metaway.converter;

import com.metaway.api.dto.request.PetRequest;
import com.metaway.api.dto.response.PetResponse;
import com.metaway.model.Cliente;
import com.metaway.model.Pet;
import com.metaway.model.Raca;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PetConverter {

    private final ClienteConverter clienteConverter;
    private final RacaConverter racaConverter;

    public Pet toModel(PetRequest petRequest){
        return Pet.builder()
                .nome(petRequest.getNome())
                .dataNascimento(petRequest.getDataNascimento())
                .cliente(Cliente.builder()
                        .id(petRequest.getCliente())
                        .build())
                .raca(Raca.builder()
                        .id(petRequest.getRaca())
                        .build())
                .build();
    }

    public PetResponse toResponse(Pet pet){

        return PetResponse.builder()
                .id(pet.getId())
                .cliente(clienteConverter.toResponse(pet.getCliente()))
                .raca(racaConverter.toResponse(pet.getRaca()))
                .dataNascimento(pet.getDataNascimento())
                .nome(pet.getNome())
                .build();

    }

    public List<PetResponse> ToListResponse(List<Pet> listEntity){
        return listEntity.stream().map(this::toResponse).toList();
    }

}
