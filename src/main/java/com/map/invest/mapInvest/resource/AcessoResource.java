package com.map.invest.mapInvest.resource;

import com.map.invest.mapInvest.api.resource.Resource;
import com.map.invest.mapInvest.dto.AcessoDTO;
import com.map.invest.mapInvest.dto.UsuarioDTO;
import com.map.invest.mapInvest.filtroDTO.UsuarioFiltroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.ws.rs.core.MediaType;
import org.springframework.http.ResponseEntity;

public interface AcessoResource extends Resource {

    @Operation(summary = "Retorna o acesso consultada pelo codigo identificador (id).", tags = ACESSO, responses = {
            @ApiResponse(responseCode = RESPONSE_OK, description = "Sucesso.", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = AcessoDTO.class))),
            @ApiResponse(responseCode = RESPONSE_BAD_REQUEST, description = "Código do acesso invalido.")})
    ResponseEntity getAcesso(Long acessoID);

}
