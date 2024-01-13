package com.map.invest.mapInvest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AcessoDTO {

    private Long acessoID;
    private Long usuarioID;
    private String login;
    private String senha;
}
