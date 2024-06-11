package com.map.invest.mapInvest.canonico;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaCanonico {

    private Long pessoaID;
    private String nome;
    private String email;
    private String ativo;
    private DocumentoPrincipalCanonico documentoPrincipal;
    private EnderecoCanonico endereco;
    private List<TelefoneCanonico> telefones;
    private AcessoCanonico acesso;
    private List<AuditoriaCanonico> auditoria;

}
