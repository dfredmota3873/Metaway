
package com.metaway.api.controller.doc;

import com.metaway.api.dto.request.PetRequest;
import com.metaway.api.dto.request.RacaRequest;
import com.metaway.api.dto.response.PetResponse;
import com.metaway.api.dto.response.RacaResponse;
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

@Tag(name = "Api de Pets")
public interface IPetController {

    @Operation(summary = "Realiza o cadastro de um pet",
            description = "Realiza o cadastro de um pet")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Caso o pet seja cadastrado com sucesso."
            )
    })
    @PostMapping(value = "",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PetResponse> cadastrar(@Valid @RequestBody PetRequest petRequest);

    @Operation(summary = "Buscar um pet por id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a consulta seja efetuada com sucesso."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhum pet."
            ),
    })
    @GetMapping(value = "/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PetResponse> buscarPorId(@PathVariable(name = "id") UUID id);

    @Operation(summary = "Listar todos os registros de pet.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a consulta seja efetuada com sucesso."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhum pet."
            ),
    })
    @GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<PetResponse>> listarTodos();

    @Operation(summary = "Realiza a atualizacao do pet",
            description = "Realiza a atualizacao da pet")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Caso o pet seja atualizado com sucesso."
            )
    })
    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE , consumes=MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PetResponse> atualizar(@PathVariable(name = "id") UUID id,@Valid @RequestBody PetRequest petRequest);

    @Operation(summary = "Realiza a deleção do pet",
            description = "Realiza a deleção da pet")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Caso o pet seja deletado com sucesso."
            )
    })
    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PetResponse> deletar(@PathVariable(name = "id") UUID id);

}
