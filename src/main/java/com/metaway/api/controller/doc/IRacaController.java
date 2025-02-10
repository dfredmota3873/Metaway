
package com.metaway.api.controller.doc;

import com.metaway.api.dto.request.RacaRequest;
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

@Tag(name = "Api de Raças")
public interface IRacaController {

    @Operation(summary = "Realiza o cadastro de um raça",
            description = "Realiza o cadastro de um raça")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Caso o raça seja cadastrado com sucesso."
            )
    })
    @PostMapping(value = "",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RacaResponse> cadastrar(@Valid @RequestBody RacaRequest racaRequest);

    @Operation(summary = "Buscar um raça por id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a consulta seja efetuada com sucesso."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhum raça."
            ),
    })
    @GetMapping(value = "/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RacaResponse> buscarPorId(@PathVariable(name = "id") UUID id);

    @Operation(summary = "Listar todos os registros de raça.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a consulta seja efetuada com sucesso."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhum raça."
            ),
    })
    @GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<RacaResponse>> listarTodos();

    @Operation(summary = "Realiza a atualizacao do raça",
            description = "Realiza a atualizacao da raça")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Caso o raça seja atualizado com sucesso."
            )
    })
    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE , consumes=MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RacaResponse> atualizar(@PathVariable(name = "id") UUID id,@Valid @RequestBody RacaRequest racaRequest);

    @Operation(summary = "Realiza a deleção do raça",
            description = "Realiza a deleção da raça")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Caso o raça seja deletado com sucesso."
            )
    })
    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RacaResponse> deletar(@PathVariable(name = "id") UUID id);

}
