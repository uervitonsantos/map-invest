package com.map.invest.mapInvest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioDTO {

    private Long usuarioID;
    private String nome;
    private String sobreNome;
    private String cpfcnpj;
    private String email;
    private String login;
    private PerfilDTO perfil;
}
