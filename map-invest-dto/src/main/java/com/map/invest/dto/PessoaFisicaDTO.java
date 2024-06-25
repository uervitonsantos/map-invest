package com.map.invest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.map.invest.util.adapter.LocalDateAdapter;
import com.map.invest.util.constantes.SexoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "PessoaFisica", description = "Dados da Pessoa Fisica")
@XmlRootElement(name = "PessoaFisica")
@XmlAccessorType(XmlAccessType.FIELD)
public class PessoaFisicaDTO {

    @Schema(description = "Identificador único da Pessoa Fisica", example = "1000")
    @XmlElement(nillable = false)
    private Long pessoaFisicaID;

    @Schema(description = "Sobrenome da pessoa", example = "Silva dos Santos")
    @XmlElement(nillable = false)
    private String sobrenome;

    @Schema(description = "Data de nascimento", example = "1988-06-15")
    @XmlElement(nillable = false)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dataNascimento;

    @Schema(description = "Sexo (M/F/O)", example = "M")
    @XmlElement(nillable = false)
    private SexoEnum sexo;

    @Schema(description = "Nacionalidade da pessoa Fisica", example = "BRASIL")
    @XmlElement(nillable = false)
    private String nacionalidade;

    @Schema(description = "Naturalidade da pessoa Fisica", example = "SAO PAULO")
    @XmlElement(nillable = false)
    private String naturalidade;

    @XmlElement(nillable = false)
    private List<DocumentosSecundariosDTO> documentosSecundarios;
}
