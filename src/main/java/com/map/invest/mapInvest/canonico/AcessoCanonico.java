package com.map.invest.mapInvest.canonico;

import com.map.invest.mapInvest.entity.Perfil;
import jakarta.persistence.Column;
import lombok.*;

import java.util.List;

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
