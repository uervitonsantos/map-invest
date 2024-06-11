package com.map.invest.mapInvest.dto;

import com.map.invest.mapInvest.util.adapter.LocalDateTimeAdapter;
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

    @Schema(description = "Identificador único do documento secundario", example = "1")
    @XmlElement(nillable = false)
    private Long documentosSecundarioslID;

    @Schema(description = "Tipo de documento (RG/CNH)", example = "1")
    @XmlElement(nillable = false)
    private String tipoDocumentosSecundarios;

    @Schema(description = "Número do documento", example = "1")
    @XmlElement(nillable = false)
    private String numeroDocumentosSecundarios;

    @Schema(description = "Órgão Emissor do documento", example = "1")
    @XmlElement(nillable = false)
    private String orgaoEmissor;

    @Schema(description = "Data de Emissão do documento", example = "1")
    @XmlElement(nillable = false)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDate dataEmissao;
}
