package com.map.invest.mapInvest.dto;

import com.map.invest.mapInvest.util.constantes.TipoPerfilEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Perfil")
@XmlRootElement(name = "Perfil")
public class PerfilDTO {

    @Schema(description = "Identificador único do perfil", example = "1")
    @XmlElement(nillable = false)
    private Long perfilID;

    @Schema(description = "Código do perfil", example = "ADM")
    @XmlElement(nillable = false)
    private String codPerfil;

    @Schema(description = "Nome do perfil", example = "ADMINISTRADOR")
    @XmlElement(nillable = false)
    private TipoPerfilEnum nomePerfil;

    @Schema(description = "Descrição do perfil", example = "Perfil de administrador do sistema")
    @XmlElement(nillable = false)
    private String descricao;

    @XmlElement(nillable = false)
    private List<PermissaoTelaDTO> permissaoTelas;

}
