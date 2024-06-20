package com.map.invest.canonico;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcessoCanonico {

    private Long acessoID;
    private Long pessoaID;
    private Long perfilID;
    private String login;
    private String senha;
    private PerfilCanonico perfis;
}
