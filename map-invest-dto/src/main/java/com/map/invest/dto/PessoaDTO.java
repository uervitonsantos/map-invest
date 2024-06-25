package com.map.invest.dto;

import com.map.invest.util.constantes.AtivoEnum;
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

    @Schema(description = "Identificador único do usuário", example = "1000")
    @XmlElement(nillable = false)
    private Long pessoaID;

    @Schema(description = "Nome do usuário", example = "Jose")
    @XmlElement(nillable = false)
    private String nome;

    @Schema(description = "Email do usuário", example = "jose.santos@example.com")
    @XmlElement(nillable = false)
    private String email;

    @Schema(description = "usuário ativo(TRUE) ou inativo(FALSE)", example = "TRUE")
    @XmlElement(nillable = false)
    private AtivoEnum ativo;

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
