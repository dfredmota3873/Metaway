package com.metaway.api.controller.impl;

import com.metaway.api.controller.doc.IPetController;
import com.metaway.api.dto.request.PetRequest;
import com.metaway.api.dto.response.PetResponse;
import com.metaway.converter.PetConverter;
import com.metaway.service.PetService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/pet")
@AllArgsConstructor
@CrossOrigin("*")
@Slf4j
public class PetController implements IPetController {

    private final PetService petService;

    private final PetConverter petConverter;

    @Override
    public ResponseEntity<PetResponse> cadastrar(PetRequest petRequest) {
        log.info("Iniciando cadastro de um pet com payload: {}", petRequest);
        var pet = petService.cadastrar(petConverter.toModel(petRequest));
        log.info("Cadastro de pet realizado com sucesso id: {}", pet.getId());
        return ResponseEntity.ok(petConverter.toResponse(pet));
    }

    @Override
    public ResponseEntity<PetResponse> buscarPorId(UUID id) {
        log.info("Iniciando busca de um pet com id: {}", id);
        var pet = petService.buscarPorId(id);
        log.info("Busca de um pet com sucesso id: {}", id);
        return ResponseEntity.ok(petConverter.toResponse(pet));
    }

    @Override
    public ResponseEntity<List<PetResponse>> listarTodos() {
        log.info("Realizando uma busca de todos os pets");
        var lista = petService.buscarTodos();
        return ResponseEntity.ok(petConverter.ToListResponse(lista));
    }

    @Override
    public ResponseEntity<PetResponse> atualizar(UUID id, PetRequest petRequest) {
        log.info("Atualizando dados de um pet com id: {}", id);
        var pet = petService.atualizar(id, petConverter.toModel(petRequest));
        log.info("Dados atualizados com sucesso id: {}", id);
        return ResponseEntity.ok(petConverter.toResponse(pet));
    }

    @Override
    public ResponseEntity<PetResponse> deletar(UUID id) {
        log.info("Deletando dados de pet com id: {}", id);
        petService.deletar(id);
        log.info("Deletado com sucesso id: {}", id);
        return ResponseEntity.ok().build();
    }
}
