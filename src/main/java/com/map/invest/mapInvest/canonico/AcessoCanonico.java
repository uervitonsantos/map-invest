package com.map.invest.mapInvest.canonico;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcessoCanonico {

    private Long acessoID;
    private Long usuarioID;
    private Long perfilID;
    private String login;
    private String senha;
    private PerfilCanonico perfis;
}
