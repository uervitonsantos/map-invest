package com.map.invest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.map.invest.util.adapter.LocalDateAdapter;
import com.map.invest.util.adapter.LocalDateTimeAdapter;
import com.map.invest.util.constantes.TipoInscricaoEnum;
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
@Schema(name = "PessoaJuridica", description = "Dados da Pessoa Juridica")
@XmlRootElement(name = "PessoaJuridica")
@XmlAccessorType(XmlAccessType.FIELD)
public class PessoaJuridicaDTO {

    @Schema(description = "Identificador único da Pessoa Juridica", example = "1000")
    @XmlElement(nillable = false)
    private Long pessoaJuridicaID;

    @Schema(description = "Nome comercial", example = "MAP INVEST")
    @XmlElement(nillable = false)
    private String nomeComercia;

    @Schema(description = "Data de constituição da empresa", example = "1988-06-15")
    @XmlElement(nillable = false)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataConstituicao;

    @Schema(description = "Tipo de inscrição (MEI/EI/LTDA/SLU/SS/SA)", example = "LTDA")
    @XmlElement(nillable = false)
    private TipoInscricaoEnum tipoInscricao;

    @Schema(description = "Número da inscrição", example = "1123564987523")
    @XmlElement(nillable = false)
    private String numeroInscricao;

    @Schema(description = "Tipo de entidade legal", example = "Serviços Financeiros")
    @XmlElement(nillable = false)
    private String naturazaJuridica;

    @Schema(description = "Setor ou indústria da empresa", example = "Financeiro")
    @XmlElement(nillable = false)
    private String ramoAtividade;

    @Schema(description = "Descrição das atividades da empresa", example = "Administraçaõ de Ativos Financeiros")
    @XmlElement(nillable = false)
    private String objetivoSocial;
}
