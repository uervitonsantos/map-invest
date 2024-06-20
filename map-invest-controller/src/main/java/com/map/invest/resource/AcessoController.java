package com.map.invest.resource;

import com.map.invest.dto.AuthRequestDTO;
import com.map.invest.dto.JwtResponseDTO;
import com.map.invest.resouce.Resource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.core.MediaType;

@Tag(name = "Acesso", description = "Operações para obter a autenticação do usuário")
public interface AcessoController extends Resource {

    @Operation(summary = "Operação para login na aplicação", description = "Operações para obter a autenticação do usuário",
            requestBody = @RequestBody(description = "Dados necessarios para a  autenticação de um usuário", required = true,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = AuthRequestDTO.class))),
            responses = {
            @ApiResponse(responseCode = Resource.RESPONSE_OK, description = "Sucesso.", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = JwtResponseDTO.class))),
            @ApiResponse(responseCode = Resource.RESPONSE_UNAUTHORIZED, description = "Usuário não autorizado", content = @Content),
            @ApiResponse(responseCode = Resource.RESPONSE_FORBIDDEN, description = "Usuário não autorizado", content = @Content)})
    JwtResponseDTO AuthenticateAndGetToken(AuthRequestDTO authRequestDTO);
}
