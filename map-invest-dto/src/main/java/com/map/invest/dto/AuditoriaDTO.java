package com.map.invest.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.map.invest.util.adapter.LocalDateAdapter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Auditoria")
@XmlRootElement(name = "Auditoria")
public class AuditoriaDTO {

    @Schema(description = "Identificador único da auditoria", example = "1")
    @XmlElement(nillable = false)
    private Long auditoriaID;

    @Schema(description = "Data de alteração do objeto", example = "2024-05-28T22:22:07.924")
    @XmlElement(nillable = false)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDateTime dataAlteracao;

    @Schema(description = "Tipo de evento no banco de dados", example = "INSERT")
    @XmlElement(nillable = false)
    private String evento;

    @Schema(description = "nome da tela", example = "USUARIO")
    @XmlElement(nillable = false)
    private String nomeTabela;

    @Schema(description = "Identificador único do registro", example = "1")
    @XmlElement(nillable = false)
    private Long registroID;

    @Schema(description = "Usuário que realizou a modificação", example = "administrador")
    @XmlElement(nillable = false)
    private String usuarioModificador;

    @Schema(description = "Objeto json com os dados antigos do registro", example = "Objeto Json")
    @XmlElement(nillable = false)
    private JsonNode dadosAntigos;

    @Schema(description = "Objeto json com os novos dados do registro", example = "Objeto Json")
    @XmlElement(nillable = false)
    private JsonNode dadosNovos;
}
