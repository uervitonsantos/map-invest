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
@Schema(name = "Acesso")
@XmlRootElement(name = "Acesso")
@XmlAccessorType(XmlAccessType.FIELD)
public class AcessoDTO {

    @Schema(description = "Identificador único do acesso", example = "1")
    @XmlElement(nillable = false)
    private Long acessoID;

    @Schema(description = "Identificador único para o tipo do perfil", example = "1")
    @XmlElement(nillable = false)
    private Long perfilID;

    @Schema(description = "login do usuário", example = "ariano.suassuna")
    @XmlElement(nillable = false)
    private String login;

    @Schema(description = "Senha do usuário", example = "$2a$10$C4T0bZ2T7.CrVINEn65zIuoEdQA1my.A.MZOLNzUQxkFjoe3MDyw2")
    @XmlElement(nillable = false)
    private String senha;

    @XmlElement(nillable = false)
    private PerfilDTO perfis;
}
