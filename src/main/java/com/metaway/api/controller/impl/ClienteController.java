package com.metaway.api.controller.impl;

import com.metaway.api.controller.doc.IClienteController;
import com.metaway.api.dto.request.ClienteRequest;
import com.metaway.api.dto.response.ClienteResponse;
import com.metaway.converter.ClienteConverter;
import com.metaway.service.ClienteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/cliente")
@AllArgsConstructor
@CrossOrigin("*")
@Slf4j
public class ClienteController implements IClienteController {

    private final ClienteService clienteService;

    private final ClienteConverter clienteConverter;

    @Override
    public ResponseEntity<ClienteResponse> cadastrar(ClienteRequest clienteRequest) {
        log.info("Iniciando cadastro de um cliente com payload: {}", clienteRequest);
        var cliente = clienteService.cadastrar(clienteConverter.toModel(clienteRequest));
        log.info("Cadastro de usuário realizado com sucesso id: {}", cliente.getId());
        return ResponseEntity.ok(clienteConverter.toResponse(cliente));
    }

    @Override
    public ResponseEntity<ClienteResponse> buscarPorId(UUID id) {
        log.info("Iniciando busca de um cliente com id: {}", id);
        var cliente = clienteService.buscarPorId(id);
        log.info("Busca de um cliente com sucesso id: {}", id);
        return ResponseEntity.ok(clienteConverter.toResponse(cliente));
    }

    @Override
    public ResponseEntity<List<ClienteResponse>> listarTodos() {
        log.info("Realizando uma busca de todos os clientes");
        var lista = clienteService.buscarTodos();
        return ResponseEntity.ok(clienteConverter.ToListResponse(lista));
    }

    @Override
    public ResponseEntity<ClienteResponse> atualizar(UUID id, ClienteRequest clienteRequest) {
        log.info("Atualizando dados de cliente com id: {}", id);
        var usuario = clienteService.atualizar(id,clienteConverter.toModel(clienteRequest));
        log.info("Dados atualizados com sucesso id: {}", id);
        return ResponseEntity.ok(clienteConverter.toResponse(usuario));
    }

    @Override
    public ResponseEntity<ClienteResponse> deletar(UUID id) {
        log.info("Deletando dados de cliente com id: {}", id);
        clienteService.deletar(id);
        log.info("Deletado com sucesso id: {}", id);
        return ResponseEntity.ok().build();
    }
}
