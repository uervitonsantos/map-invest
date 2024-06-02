package com.map.invest.mapInvest.canonico;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditoriaCanonico {

    private Long auditoriaID;
    private LocalDateTime dataAlteracao;
    private String evento;
    private String nomeTabela;
    private Long registroID;
    private String usuarioModificador;
    private JsonNode dadosAntigos;
    private JsonNode dadosNovos;

}
