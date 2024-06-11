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
@Schema(name = "PessoaJuridica", description = "Dados da PessoaJ uridica")
@XmlRootElement(name = "PessoaJuridica")
@XmlAccessorType(XmlAccessType.FIELD)
public class PessoaJuridicaDTO {

    @Schema(description = "Identificador único da Pessoa Juridica", example = "1")
    @XmlElement(nillable = false)
    private Long pessoaJuridicaID;

    @Schema(description = "Nome comercial", example = "1")
    @XmlElement(nillable = false)
    private String nomeComercia;

    @Schema(description = "Data de constituição da empresa", example = "1")
    @XmlElement(nillable = false)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDate dataConstituicao;

    @Schema(description = "Tipo de inscrição (ESTADUAL/MUNICIPAL)", example = "1")
    @XmlElement(nillable = false)
    private String tipoInscricao;

    @Schema(description = "Número da inscrição", example = "1")
    @XmlElement(nillable = false)
    private String numeroInscricao;

    @Schema(description = "Tipo de entidade legal", example = "1")
    @XmlElement(nillable = false)
    private String naturazaJuridica;

    @Schema(description = "Setor ou indústria da empresa", example = "1")
    @XmlElement(nillable = false)
    private String ramoAtividade;

    @Schema(description = "Descrição das atividades da empresa", example = "1")
    @XmlElement(nillable = false)
    private String objetivoSocial;
}
