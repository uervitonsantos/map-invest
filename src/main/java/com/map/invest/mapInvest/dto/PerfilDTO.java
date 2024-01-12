package com.map.invest.mapInvest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PerfilDTO {

    private Long perfilID;
    private String codPerfil;
    private String nomePerfil;
    private String descricao;
    private List<PermissaoTelaDTO> permissaoTelas;

}
