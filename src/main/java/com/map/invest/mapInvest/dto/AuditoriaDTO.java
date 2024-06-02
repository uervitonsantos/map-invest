package com.map.invest.mapInvest.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class AuditoriaDTO {

    private Long auditoriaID;
    private LocalDateTime dataAlteracao;
    private String evento;
    private String nomeTabela;
    private Long registroID;
    private String usuarioModificador;
    private JsonNode dadosAntigos;
    private JsonNode dadosNovos;
}
