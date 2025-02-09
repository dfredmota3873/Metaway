
package com.metaway.api.controller.doc;

import com.metaway.api.dto.request.UsuarioRequest;
import com.metaway.api.dto.response.UsuarioResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Api de Usuários")
public interface IUsuarioController {

    @Operation(summary = "Realiza o cadastro de um usuário",
            description = "Realiza o cadastro de um usuário")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Caso o usuário seja cadastrado com sucesso."
            )
    })
    @PostMapping(value = "",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UsuarioResponse> cadastrar(@Valid @RequestBody UsuarioRequest usuarioRequest);

    @Operation(summary = "Buscar um usuário por id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a consulta seja efetuada com sucesso."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhum usuario."
            ),
    })
    @GetMapping(value = "/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable(name = "id") UUID id);

    @Operation(summary = "Listar todos os registros de usuários.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a consulta seja efetuada com sucesso."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhum usuário."
            ),
    })
    @GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UsuarioResponse>> listarTodos();

    @Operation(summary = "Realiza a atualizacao do usuário",
            description = "Realiza a atualizacao da usuário")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Caso o usuário seja atualizado com sucesso."
            )
    })
    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE , consumes=MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UsuarioResponse> atualizar(@PathVariable(name = "id") UUID id,@Valid @RequestBody UsuarioRequest usuarioRequest);

//    @Operation(summary = "Realiza o carregamento das contas via Csv",
//            description = "Realiza o carregamento das contas via Csv")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "201",
//                    description = "Caso a carga seja realizada com sucesso."
//            )
//    })
//    @PostMapping(value = "/carregarCsv",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    ResponseEntity<Void> carregarCsv(@RequestParam("file") MultipartFile file);


}
