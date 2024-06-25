package com.map.invest.canonico;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.map.invest.util.constantes.SexoEnum;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaFisicaCanonico {

    private Long pessoaFisicaID;
    private Long documentoPrincipalID;
    private String sobrenome;
    private LocalDate dataNascimento;
    private SexoEnum sexo;
    private String nacionalidade;
    private String naturalidade;
    private List<DocumentosSecundariosCanonico> documentosSecundarios;
}
