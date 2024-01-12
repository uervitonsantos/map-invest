package com.map.invest.mapInvest.canonico;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioCanonico {

    private Long usuarioID;
    private Long perfilID;
    private String nome;
    private String sobreNome;
    private String cpfcnpj;
    private String email;
    private String login;
    private PerfilCanonico perfil;

}
