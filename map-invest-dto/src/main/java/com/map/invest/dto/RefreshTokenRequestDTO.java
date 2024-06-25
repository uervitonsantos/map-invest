package com.map.invest.dto;

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
@Schema(name = "RefreshToken", description = "Dados de acesso do usuário")
@XmlRootElement(name = "RefreshToken")
@XmlAccessorType(XmlAccessType.FIELD)
public class RefreshTokenRequestDTO {

    @Schema(description = "Refresh Token para renovar o access token", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbmlzdHJhZG9yIiwiaWF0IjoxNzE3NzE1NDI4LCJleHAiOjE3MTc3MTkwMjh9.83-68OfdSWiWUKFMPV1QSKh3ZJgAXbOLMJG1r38wJ6k")
    @XmlElement(nillable = false)
    private String refreshToken;
}
