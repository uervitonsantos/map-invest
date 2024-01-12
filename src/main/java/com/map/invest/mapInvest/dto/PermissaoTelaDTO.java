package com.map.invest.mapInvest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PermissaoTelaDTO {

    private Long permissaoTelaID;
    private String nomePermissao;
    private String descricao;

}
