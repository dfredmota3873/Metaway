package com.metaway.api.controller.impl;

import com.metaway.api.controller.doc.IContatoController;
import com.metaway.api.dto.request.ContatoRequest;
import com.metaway.api.dto.response.ContatoResponse;
import com.metaway.converter.ContatoConverter;
import com.metaway.service.ContatoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/contato")
@AllArgsConstructor
@CrossOrigin("*")
@Slf4j
public class ContatoController implements IContatoController {

    private final ContatoService contatoService;

    private final ContatoConverter contatoConverter;


    @Override
    public ResponseEntity<ContatoResponse> cadastrar(ContatoRequest contatoRequest) {
        log.info("Iniciando cadastro de um contato com payload: {}", contatoRequest);
        var cliente = contatoService.cadastrar(contatoConverter.toModel(contatoRequest));
        log.info("Cadastro de contato realizado com sucesso id: {}", cliente.getId());
        return ResponseEntity.ok(contatoConverter.toResponse(cliente));
    }

    @Override
    public ResponseEntity<ContatoResponse> buscarPorId(UUID id) {
        log.info("Iniciando busca de um contato com id: {}", id);
        var cliente = contatoService.buscarPorId(id);
        log.info("Busca de um contato com sucesso id: {}", id);
        return ResponseEntity.ok(contatoConverter.toResponse(cliente));
    }

    @Override
    public ResponseEntity<List<ContatoResponse>> listarTodos() {
        log.info("Realizando uma busca de todos os contatos");
        var lista = contatoService.buscarTodos();
        return ResponseEntity.ok(contatoConverter.ToListResponse(lista));
    }

    @Override
    public ResponseEntity<ContatoResponse> atualizar(UUID id, ContatoRequest request) {
        log.info("Atualizando dados de contato com id: {}", id);
        var usuario = contatoService.atualizar(id, contatoConverter.toModel(request));
        log.info("Dados atualizados com sucesso id: {}", id);
        return ResponseEntity.ok(contatoConverter.toResponse(usuario));
    }

    @Override
    public ResponseEntity<ContatoResponse> deletar(UUID id) {
        log.info("Deletando dados de contato com id: {}", id);
        contatoService.deletar(id);
        log.info("Deletado com sucesso id: {}", id);
        return ResponseEntity.ok().build();
    }
}
