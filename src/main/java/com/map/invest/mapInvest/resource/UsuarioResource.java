package com.map.invest.mapInvest.resource;

import com.map.invest.mapInvest.api.resource.Resource;
import com.map.invest.mapInvest.dto.UsuarioDTO;
import com.map.invest.mapInvest.filtroDTO.UsuarioFiltroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.http.ResponseEntity;

public interface UsuarioResource extends Resource {

    @Operation(summary = "Retorna o usuario consultada pelo codigo identificador (id).", tags = USUARIO, responses = {
            @ApiResponse(responseCode = RESPONSE_OK, description = "Sucesso.", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UsuarioDTO.class))),
            @ApiResponse(responseCode = RESPONSE_BAD_REQUEST, description = "Código do usuario invalido.")})
    ResponseEntity getUsuario(Long usuarioID);

    @Operation(summary = "Retorna lista de Usuarios", tags = USUARIO, responses = {
            @ApiResponse(responseCode = RESPONSE_OK, description = "Sucesso.", content = @Content(mediaType = MediaType.APPLICATION_JSON, array = @ArraySchema(schema = @Schema(implementation = UsuarioDTO.class)))),})
    ResponseEntity getUsuarios(UsuarioFiltroDTO filtro);

    @Operation(summary = "Adiciona um novo usuario", tags = USUARIO, responses = {
            @ApiResponse(responseCode = RESPONSE_OK, description = "Sucesso.", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UsuarioDTO.class))),
            @ApiResponse(responseCode = RESPONSE_BAD_REQUEST, description = "Erro ao criar usuario.")})
    Response criaUsuario(UsuarioDTO dto);

    @Operation(summary = "Atualiza usuario", tags = USUARIO, responses = {
            @ApiResponse(responseCode = RESPONSE_OK, description = "Sucesso.", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UsuarioDTO.class))),
            @ApiResponse(responseCode = RESPONSE_BAD_REQUEST, description = "Erro ao atualizar o usuario.")})
    Response atualizaUsuario(Long usuarioID, UsuarioDTO dto);

    @Operation(summary = "Remove usuario", tags = USUARIO, responses = {
            @ApiResponse(responseCode = RESPONSE_NO_CONTENT, description = "Sucesso."),
            @ApiResponse(responseCode = RESPONSE_BAD_REQUEST, description = "Erro ao remover usuario.")})
    Response removeUsuario(Long usuarioID);
}
