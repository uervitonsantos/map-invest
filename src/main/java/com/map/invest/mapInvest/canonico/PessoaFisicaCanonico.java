package com.map.invest.mapInvest.canonico;

import com.map.invest.mapInvest.util.constantes.SexoEnum;
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
