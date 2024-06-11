package com.map.invest.mapInvest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.map.invest.mapInvest.filtro.PessoaFisicaFiltro;
import com.map.invest.mapInvest.filtro.PessoaJuridicaFiltro;
import com.map.invest.mapInvest.util.constantes.TipoDocumentoEnum;
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
@Schema(name = "DocumentoPrincipal")
@XmlRootElement(name = "DocumentoPrincipal")
@XmlAccessorType(XmlAccessType.FIELD)
public class DocumentoPrincipalDTO {

    @Schema(description = "Identificador único do documento", example = "1")
    @XmlElement(nillable = false)
    private Long documentoPrincipalID;

    @Schema(description = "Tipo do documento pode ser CPF ou CNPJ", example = "CPF")
    @XmlElement(nillable = false)
    private TipoDocumentoEnum tipoDocumentoPrincipal;

    @Schema(description = "Número do docuneto", example = "232.630.167-68")
    @XmlElement(nillable = false)
    private String numeroDocumentoPrincipal;

    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = PessoaFisicaFiltro.class)
    @XmlElement(nillable = false)
    private PessoaFisicaDTO pessoaFisica;

    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = PessoaJuridicaFiltro.class)
    @XmlElement(nillable = false)
    private PessoaJuridicaDTO pessoaJuridica;

}
