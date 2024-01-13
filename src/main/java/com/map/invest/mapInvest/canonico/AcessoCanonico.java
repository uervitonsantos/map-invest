package com.map.invest.mapInvest.canonico;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AcessoCanonico {

    private Long acessoID;
    private Long usuarioID;
    private String login;
    private String senha;
}
