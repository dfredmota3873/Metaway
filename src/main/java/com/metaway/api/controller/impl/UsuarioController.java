package com.metaway.api.controller.impl;

import com.metaway.api.controller.doc.IUsuarioController;
import com.metaway.api.dto.request.UsuarioRequest;
import com.metaway.api.dto.response.UsuarioResponse;
import com.metaway.converter.UsuarioConverter;
import com.metaway.service.UsuarioService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/usuario")
@AllArgsConstructor
@CrossOrigin("*")
@Slf4j
public class UsuarioController implements IUsuarioController {

    private final UsuarioService usuarioService;

    private final UsuarioConverter converter;


    @Override
    public ResponseEntity<UsuarioResponse> cadastrar(UsuarioRequest usuarioRequest) {
        log.info("Iniciando cadastro de um usuário com payload: {}", usuarioRequest);
        var usuario = usuarioService.cadastrar(converter.toModel(usuarioRequest));
        log.info("Cadastro de usuário realizado com sucesso id: {}", usuario.getId());
        return ResponseEntity.ok(converter.toResponse(usuario));
    }

    @Override
    public ResponseEntity<UsuarioResponse> buscarPorId(UUID id) {
        log.info("Iniciando busca de um usuário com id: {}", id);
        var usuario = usuarioService.buscarPorId(id);
        log.info("Busca de um usuário com sucesso id: {}", id);
        return ResponseEntity.ok(converter.toResponse(usuario));
    }

    @Override
    public ResponseEntity<List<UsuarioResponse>> listarTodos() {
        log.info("Realizando uma busca de todos os usuarios");
        var lista = usuarioService.buscarTodos();
        return ResponseEntity.ok(converter.ToListResponse(lista));

    }

    @Override
    public ResponseEntity<UsuarioResponse> atualizar(UUID id, UsuarioRequest usuarioRequest) {
        log.info("Atualizando dados de usuario com id: {}", id);
        var usuario = usuarioService.atualizar(converter.toModel(usuarioRequest),id);
        log.info("Dados atualizados com sucesso id: {}", id);
        return ResponseEntity.ok(converter.toResponse(usuario));
    }

    @Override
    public ResponseEntity<UsuarioResponse> deletar(UUID id) {
        log.info("Deletando dados de usuario com id: {}", id);
        usuarioService.deletar(id);
        log.info("Deletado com sucesso id: {}", id);
        return ResponseEntity.ok().build();
    }


}
