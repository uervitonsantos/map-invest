package com.map.invest.mapInvest.dto;

import com.map.invest.mapInvest.util.constantes.TipoTelefoneEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Telefone")
@XmlRootElement(name = "Telefone")
@XmlAccessorType(XmlAccessType.FIELD)
public class TelefoneDTO {

    @Schema(description = "Identificador único do telefone", example = "1")
    @XmlElement(nillable = false)
    private Long telefoneID;

    @Schema(description = "Código identificador do telefone", example = "55")
    @XmlElement(nillable = false)
    private String codigo;

    @Schema(description = "Tipo de telefone pode ser CELULAR, RESIDENCIAL ou COMERCIAL ", example = "CELULAR")
    @XmlElement(nillable = false)
    private TipoTelefoneEnum tipoTelefone;

    @Schema(description = "Número do telefone", example = "11987654321")
    @XmlElement(nillable = false)
    private String numeroTelefone;
}
