package com.map.invest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "PermissaoTela")
@XmlRootElement(name = "PermissaoTela")
public class PermissaoTelaDTO {

    @Schema(description = "Identificador único da permissão de tela", example = "1")
    @XmlElement(nillable = false)
    private Long permissaoTelaID;

    @Schema(description = "Nome da permissão de tela", example = "CADASTRAR")
    @XmlElement(nillable = false)
    private String nomePermissao;

    @Schema(description = "Descrição da permissão de tela", example = "Permissão para cadastrar dados na tela")
    @XmlElement(nillable = false)
    private String descricao;

}
