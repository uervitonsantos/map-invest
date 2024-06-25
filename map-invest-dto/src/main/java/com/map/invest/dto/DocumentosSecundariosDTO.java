package com.map.invest.dto;

import com.map.invest.util.adapter.LocalDateTimeAdapter;
import com.map.invest.util.constantes.TipoDocumentoSecundariosEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "DocumentosSecundarios")
@XmlRootElement(name = "DocumentosSecundarios")
@XmlAccessorType(XmlAccessType.FIELD)
public class DocumentosSecundariosDTO {

    @Schema(description = "Identificador único do documento secundario", example = "1000")
    @XmlElement(nillable = false)
    private Long documentosSecundarioslID;

    @Schema(description = "Tipo de documento (RG/CNH)", example = "RG")
    @XmlElement(nillable = false)
    private TipoDocumentoSecundariosEnum tipoDocumentosSecundarios;

    @Schema(description = "Número do documento", example = "152740934")
    @XmlElement(nillable = false)
    private String numeroDocumentosSecundarios;

    @Schema(description = "Órgão Emissor do documento", example = "ssp")
    @XmlElement(nillable = false)
    private String orgaoEmissor;

    @Schema(description = "Data de Emissão do documento", example = "2011-02-15")
    @XmlElement(nillable = false)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDate dataEmissao;
}
