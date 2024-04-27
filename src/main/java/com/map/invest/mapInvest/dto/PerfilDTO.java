package com.map.invest.mapInvest.dto;

import com.map.invest.mapInvest.util.constantes.TipoPerfilEnum;
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
    private TipoPerfilEnum nomePerfil;
    private String descricao;
    private List<PermissaoTelaDTO> permissaoTelas;

}
