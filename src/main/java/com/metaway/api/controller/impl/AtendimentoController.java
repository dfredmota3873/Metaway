package com.metaway.api.controller.impl;

import com.metaway.api.controller.doc.IAtendimentoController;
import com.metaway.api.dto.request.AtendimentoRequest;
import com.metaway.api.dto.response.AtendimentoResponse;
import com.metaway.converter.AtendimentoConverter;
import com.metaway.service.AtendimentoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/atendimento")
@AllArgsConstructor
@CrossOrigin("*")
@Slf4j
public class AtendimentoController implements IAtendimentoController {

    private final AtendimentoService atendimentoService;

    private final AtendimentoConverter atendimentoConverter;


    @Override
    public ResponseEntity<AtendimentoResponse> cadastrar(AtendimentoRequest atendimentoRequest) {
        log.info("Iniciando cadastro de um atendimento com payload: {}", atendimentoRequest);
        var atendimento = atendimentoService.cadastrar(atendimentoConverter.toModel(atendimentoRequest));
        log.info("Cadastro de atendimento realizado com sucesso id: {}", atendimento.getId());
        return ResponseEntity.ok(atendimentoConverter.toResponse(atendimento));
    }

    @Override
    public ResponseEntity<AtendimentoResponse> buscarPorId(UUID id) {
        log.info("Iniciando busca de um atendimento com id: {}", id);
        var atendimento = atendimentoService.buscarPorId(id);
        log.info("Busca de um atendimento com sucesso id: {}", id);
        return ResponseEntity.ok(atendimentoConverter.toResponse(atendimento));
    }

    @Override
    public ResponseEntity<List<AtendimentoResponse>> listarTodos() {
        log.info("Realizando uma busca de todos os atendimentos");
        var lista = atendimentoService.buscarTodos();
        return ResponseEntity.ok(atendimentoConverter.ToListResponse(lista));
    }

    @Override
    public ResponseEntity<AtendimentoResponse> atualizar(UUID id, AtendimentoRequest atendimentoRequest) {
        log.info("Atualizando dados de atendimento com id: {}", id);
        var atendimento = atendimentoService.atualizar(id,atendimentoConverter.toModel(atendimentoRequest));
        log.info("Dados atualizados com sucesso id: {}", id);
        return ResponseEntity.ok(atendimentoConverter.toResponse(atendimento));
    }

    @Override
    public ResponseEntity<AtendimentoResponse> deletar(UUID id) {
        log.info("Deletando dados de atendimento com id: {}", id);
        atendimentoService.deletar(id);
        log.info("Deletado com sucesso id: {}", id);
        return ResponseEntity.ok().build();
    }
}
