package com.map.invest.mapInvest.canonico;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PermissaoTelaCanonico {
    private Long permissaoTelaID;
    private String nomePermissao;
    private String descricao;

}
