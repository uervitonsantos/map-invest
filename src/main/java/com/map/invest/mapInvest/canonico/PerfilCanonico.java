package com.map.invest.mapInvest.canonico;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PerfilCanonico {
    private Long perfilID;
    private String codPerfil;
    private String nomePerfil;
    private String descricao;
    private List<PermissaoTelaCanonico> permissaoTelas;

}
