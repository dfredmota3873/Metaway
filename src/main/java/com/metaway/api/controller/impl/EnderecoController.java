package com.metaway.api.controller.impl;

import com.metaway.api.controller.doc.IEnderecoController;
import com.metaway.api.dto.request.EnderecoRequest;
import com.metaway.api.dto.response.EnderecoResponse;
import com.metaway.converter.EnderecoConverter;
import com.metaway.service.EnderecoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/endereco")
@AllArgsConstructor
@CrossOrigin("*")
@Slf4j
public class EnderecoController  implements IEnderecoController {

    private final EnderecoService enderecoService;

    private final EnderecoConverter enderecoConverter;


    @Override
    public ResponseEntity<EnderecoResponse> cadastrar(EnderecoRequest enderecoRequest) {
        log.info("Iniciando cadastro de um endereço com payload: {}", enderecoRequest);
        var endereco = enderecoService.cadastrar(enderecoConverter.toModel(enderecoRequest));
        log.info("Cadastro de endereço realizado com sucesso id: {}", endereco.getId());
        return ResponseEntity.ok(enderecoConverter.toResponse(endereco));
    }

    @Override
    public ResponseEntity<EnderecoResponse> buscarPorId(UUID id) {
        log.info("Iniciando busca de um endereço com id: {}", id);
        var cliente = enderecoService.buscarPorId(id);
        log.info("Busca de um cliente com sucesso id: {}", id);
        return ResponseEntity.ok(enderecoConverter.toResponse(cliente));
    }

    @Override
    public ResponseEntity<List<EnderecoResponse>> listarTodos() {
        log.info("Realizando uma busca de todos os endereços");
        var lista = enderecoService.buscarTodos();
        return ResponseEntity.ok(enderecoConverter.ToListResponse(lista));
    }

    @Override
    public ResponseEntity<EnderecoResponse> atualizar(UUID id, EnderecoRequest enderecoRequest) {
        log.info("Atualizando dados de endereço com id: {}", id);
        var usuario = enderecoService.atualizar(id,enderecoConverter.toModel(enderecoRequest));
        log.info("Dados atualizados com sucesso id: {}", id);
        return ResponseEntity.ok(enderecoConverter.toResponse(usuario));
    }

    @Override
    public ResponseEntity<EnderecoResponse> deletar(UUID id) {
        log.info("Deletando dados de endereço com id: {}", id);
        enderecoService.deletar(id);
        log.info("Deletado com sucesso id: {}", id);
        return ResponseEntity.ok().build();
    }
}
