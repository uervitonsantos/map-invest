package com.map.invest.mapInvest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
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
@Schema(name = "Usuario", description = "Dados do usuário")
@XmlRootElement(name = "Usuario")
@XmlAccessorType(XmlAccessType.FIELD)
public class PessoaDTO {

    @Schema(description = "Identificador único do usuário", example = "1")
    @XmlElement(nillable = false)
    private Long pessoaID;

    @Schema(description = "Nome do usuário", example = "Ariano")
    @XmlElement(nillable = false)
    private String nome;

    @Schema(description = "Email do usuário", example = "ariano.suassuna@example.com")
    @XmlElement(nillable = false)
    private String email;

    @Schema(description = "usuário ativo ou inativo", example = "Y")
    @XmlElement(nillable = false)
    private String ativo;

    @XmlElement(nillable = false)
    private DocumentoPrincipalDTO documentoPrincipal;

    @XmlElement(nillable = false)
    private EnderecoDTO endereco;

    @XmlElement(nillable = false)
    private List<TelefoneDTO> telefones;

    @XmlElement(nillable = false)
    private AcessoDTO acesso;

    @XmlElement(nillable = false)
    private List<AuditoriaDTO> auditoria;
}
