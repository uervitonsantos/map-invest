package com.map.invest.mapInvest.dto;

import com.map.invest.mapInvest.canonico.PerfilCanonico;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AcessoDTO {

    private Long acessoID;
    private Long perfilID;
    private String login;
    private String senha;
    private PerfilDTO perfis;
}
