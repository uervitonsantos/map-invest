package com.map.invest.mapInvest.canonico;

import com.map.invest.mapInvest.entity.Pessoa;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaJuridicaCanonico {

    private Long pessoaJuridicaID;
    private Long documentoPrincipalID;
    private String nomeComercia;
    private LocalDate dataConstituicao;
    private String tipoInscricao;
    private String numeroInscricao;
    private String naturazaJuridica;
    private String ramoAtividade;
    private String objetivoSocial;
}
