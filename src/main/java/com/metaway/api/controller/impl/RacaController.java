package com.metaway.api.controller.impl;

import com.metaway.api.controller.doc.IRacaController;
import com.metaway.api.dto.request.RacaRequest;
import com.metaway.api.dto.response.RacaResponse;
import com.metaway.converter.RacaConverter;
import com.metaway.service.RacaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/raca")
@AllArgsConstructor
@CrossOrigin("*")
@Slf4j
public class RacaController implements IRacaController {

    private final RacaService racaService;

    private final RacaConverter racaConverter;


    @Override
    public ResponseEntity<RacaResponse> cadastrar(RacaRequest racaRequest) {
        log.info("Iniciando cadastro de uma raça com payload: {}", racaRequest);
        var raca = racaService.cadastrar(racaConverter.toModel(racaRequest));
        log.info("Cadastro de raça realizado com sucesso id: {}", raca.getId());
        return ResponseEntity.ok(racaConverter.toResponse(raca));
    }

    @Override
    public ResponseEntity<RacaResponse> buscarPorId(UUID id) {
        log.info("Iniciando busca de uma raça com id: {}", id);
        var raca = racaService.buscarPorId(id);
        log.info("Busca de um raça com sucesso id: {}", id);
        return ResponseEntity.ok(racaConverter.toResponse(raca));
    }

    @Override
    public ResponseEntity<List<RacaResponse>> listarTodos() {
        log.info("Realizando uma busca de todos as raças");
        var lista = racaService.buscarTodos();
        return ResponseEntity.ok(racaConverter.ToListResponse(lista));
    }

    @Override
    public ResponseEntity<RacaResponse> atualizar(UUID id, RacaRequest racaRequest) {
        log.info("Atualizando dados de raça com id: {}", id);
        var contato = racaService.atualizar(id, racaConverter.toModel(racaRequest));
        log.info("Dados atualizados com sucesso id: {}", id);
        return ResponseEntity.ok(racaConverter.toResponse(contato));
    }

    @Override
    public ResponseEntity<RacaResponse> deletar(UUID id) {
        log.info("Deletando dados de raça com id: {}", id);
        racaService.deletar(id);
        log.info("Deletado com sucesso id: {}", id);
        return ResponseEntity.ok().build();
    }
}
