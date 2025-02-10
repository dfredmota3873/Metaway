
package com.metaway.api.controller.doc;

import com.metaway.api.dto.request.EnderecoRequest;
import com.metaway.api.dto.response.EnderecoResponse;
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

@Tag(name = "Api de Endereços")
public interface IEnderecoController {

    @Operation(summary = "Realiza o cadastro de um endereço",
            description = "Realiza o cadastro de um endereço")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Caso o endereço seja cadastrado com sucesso."
            )
    })
    @PostMapping(value = "",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<EnderecoResponse> cadastrar(@Valid @RequestBody EnderecoRequest enderecoRequest);

    @Operation(summary = "Buscar um endereço por id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a consulta seja efetuada com sucesso."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhum endereço."
            ),
    })
    @GetMapping(value = "/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<EnderecoResponse> buscarPorId(@PathVariable(name = "id") UUID id);

    @Operation(summary = "Listar todos os registros de endereço.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a consulta seja efetuada com sucesso."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhum endereço."
            ),
    })
    @GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<EnderecoResponse>> listarTodos();

    @Operation(summary = "Realiza a atualizacao do endereço",
            description = "Realiza a atualizacao da endereço")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Caso o endereço seja atualizado com sucesso."
            )
    })
    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE , consumes=MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<EnderecoResponse> atualizar(@PathVariable(name = "id") UUID id,@Valid @RequestBody EnderecoRequest enderecoRequest);

    @Operation(summary = "Realiza a deleção do endereço",
            description = "Realiza a deleção da endereço")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Caso o endereço seja deletado com sucesso."
            )
    })
    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<EnderecoResponse> deletar(@PathVariable(name = "id") UUID id);

}
