package com.map.invest.mapInvest.resource;

import com.map.invest.mapInvest.api.resource.Resource;
import com.map.invest.mapInvest.dto.PessoaDTO;
import com.map.invest.mapInvest.filtroDTO.PessoaFiltroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.core.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "Usuário", description = "Operações para o recurso de usuário")
public interface PessoaController extends Resource {

    @Operation(summary = "Retorna o usuário consultada pelo codigo identificador (id)", description = "Retorna um usuário cadastrado", responses = {
            @ApiResponse(responseCode = RESPONSE_OK, description = "Sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(description = "Objeto Json retornado na busca do usuário", implementation = PessoaDTO.class))),
            @ApiResponse(responseCode = RESPONSE_BAD_REQUEST, description = "Código do usuário invalido", content = @Content),
            @ApiResponse(responseCode = RESPONSE_UNAUTHORIZED, description = "Usuário não autorizado", content = @Content),
            @ApiResponse(responseCode = RESPONSE_FORBIDDEN, description = "Usuário não autorizado", content = @Content)})
    ResponseEntity getPessoa(@Parameter(description = "Código identificador do usuário") Long pessoaID);

    @Operation(summary = "Retorna lista de usuários", description = "Retorna uma lista de todos os usuários cadastrados", responses = {
            @ApiResponse(responseCode = RESPONSE_OK, description = "Sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, array = @ArraySchema(schema = @Schema(description = "Objeto Json retornado na busca do usuário", implementation = PessoaDTO.class)))),
            @ApiResponse(responseCode = RESPONSE_UNAUTHORIZED, description = "Usuário não autorizado", content = @Content),
            @ApiResponse(responseCode = RESPONSE_FORBIDDEN, description = "Usuário não autorizado", content = @Content)})
    ResponseEntity getPessoas(@Parameter(description = "Filtros permitidos para a buscar de usuários") PessoaFiltroDTO filtro);

    @Operation(summary = "Cria um novo usuário", description = "Cria um novo usuário e adiciona a lista de usuários cadastrados",
            requestBody = @RequestBody(description = "Dados necessarios para a  criação de um novo usuário", required = true,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = REF_USUARIO_POST_REQUEST))),
            responses = {
                    @ApiResponse(responseCode = RESPONSE_OK, description = "Sucesso.", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(description = "Objeto Json retornado na criação do usuário", implementation = PessoaDTO.class))),
                    @ApiResponse(responseCode = RESPONSE_BAD_REQUEST, description = "Erro ao criar novo usuário", content = @Content)})
    ResponseEntity criaPessoa(PessoaDTO dto);

    @Operation(summary = "Atualiza um usuário", description = "Atualiza um usuário cadastrado",
            requestBody = @RequestBody(description = "Dados necessarios para a  atualização de um usuário", required = true,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = REF_USUARIO_PUT_REQUEST))),
            responses = {
                    @ApiResponse(responseCode = RESPONSE_OK, description = "Sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(description = "Objeto Json retornado na atualização do usuário", implementation = PessoaDTO.class))),
                    @ApiResponse(responseCode = RESPONSE_BAD_REQUEST, description = "Erro ao atualizar o usuário", content = @Content),
                    @ApiResponse(responseCode = RESPONSE_UNAUTHORIZED, description = "Usuário não autorizado", content = @Content),
                    @ApiResponse(responseCode = RESPONSE_FORBIDDEN, description = "Usuário não autorizado", content = @Content)})
    ResponseEntity atualizaPessoa(@Parameter(description = "Código identificador do usuário") Long pessoaID, PessoaDTO dto);

    @Operation(summary = "Remove um usuário", description = "Remove um usuário cadastrado", responses = {
            @ApiResponse(responseCode = RESPONSE_NO_CONTENT, description = "Usuário removido com sucesso"),
            @ApiResponse(responseCode = RESPONSE_BAD_REQUEST, description = "Erro ao remover usuário", content = @Content),
            @ApiResponse(responseCode = RESPONSE_UNAUTHORIZED, description = "Usuário não autorizado", content = @Content),
            @ApiResponse(responseCode = RESPONSE_FORBIDDEN, description = "Usuário não autorizado", content = @Content)})
    ResponseEntity removePessoa(@Parameter(description = "Código identificador do usuário") Long pessoaID);
}
