
package com.metaway.api.controller.doc;

import com.metaway.api.dto.request.AtendimentoRequest;
import com.metaway.api.dto.request.PetRequest;
import com.metaway.api.dto.response.AtendimentoResponse;
import com.metaway.api.dto.response.PetResponse;
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

@Tag(name = "Api de Atendimentos")
public interface IAtendimentoController {

    @Operation(summary = "Realiza o cadastro de um atendimento",
            description = "Realiza o cadastro de um atendimento")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Caso o atendimento seja cadastrado com sucesso."
            )
    })
    @PostMapping(value = "",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AtendimentoResponse> cadastrar(@Valid @RequestBody AtendimentoRequest atendimentoRequest);

    @Operation(summary = "Buscar um atendimento por id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a consulta seja efetuada com sucesso."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhum atendimento."
            ),
    })
    @GetMapping(value = "/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AtendimentoResponse> buscarPorId(@PathVariable(name = "id") UUID id);

    @Operation(summary = "Listar todos os registros de atendimento.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a consulta seja efetuada com sucesso."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhum atendimento."
            ),
    })
    @GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<AtendimentoResponse>> listarTodos();

    @Operation(summary = "Realiza a atualizacao do atendimento",
            description = "Realiza a atualizacao da atendimento")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Caso o atendimento seja atualizado com sucesso."
            )
    })
    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE , consumes=MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AtendimentoResponse> atualizar(@PathVariable(name = "id") UUID id,
                                                  @Valid @RequestBody AtendimentoRequest atendimentoRequest);

    @Operation(summary = "Realiza a deleção do atendimento",
            description = "Realiza a deleção da atendimento")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Caso o atendimento seja deletado com sucesso."
            )
    })
    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AtendimentoResponse> deletar(@PathVariable(name = "id") UUID id);

}
