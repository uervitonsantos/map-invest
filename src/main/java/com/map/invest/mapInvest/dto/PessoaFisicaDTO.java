package com.map.invest.mapInvest.dto;

import com.map.invest.mapInvest.canonico.DocumentosSecundariosCanonico;
import com.map.invest.mapInvest.util.adapter.LocalDateAdapter;
import com.map.invest.mapInvest.util.adapter.LocalDateTimeAdapter;
import com.map.invest.mapInvest.util.constantes.SexoEnum;
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

    @Schema(description = "Identificador único da Pessoa Fisica", example = "1")
    @XmlElement(nillable = false)
    private Long pessoaFisicaID;

    @Schema(description = "Sobrenome da pessoa", example = "Y")
    @XmlElement(nillable = false)
    private String sobrenome;

    @Schema(description = "Data de nascimento", example = "Y")
    @XmlElement(nillable = false)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dataNascimento;

    @Schema(description = "Sexo (M/F/O)", example = "Y")
    @XmlElement(nillable = false)
    private SexoEnum sexo;

    @Schema(description = "Nacionalidade da pessoa Fisica", example = "Y")
    @XmlElement(nillable = false)
    private String nacionalidade;

    @Schema(description = "Naturalidade da pessoa Fisica", example = "Y")
    @XmlElement(nillable = false)
    private String naturalidade;

    @XmlElement(nillable = false)
    private List<DocumentosSecundariosDTO> documentosSecundarios;
}
