package com.map.invest.mapInvest.dto;

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
@Schema(name = "AuthRequest", description = "Dados de acesso do usuário")
@XmlRootElement(name = "AuthRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class AuthRequestDTO {

    @Schema(description = "login do usuário", example = "ariano.suassuna")
    @XmlElement(nillable = false)
    private String username;

    @Schema(description = "Senha do usuário", example = "SenhaForte@123")
    @XmlElement(nillable = false)
    private String password;
}
