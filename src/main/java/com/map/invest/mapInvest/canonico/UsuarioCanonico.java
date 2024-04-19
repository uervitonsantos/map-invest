package com.map.invest.mapInvest.canonico;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UsuarioCanonico {

    private Long usuarioID;
    private Long perfilID;
    private String nome;
    private String sobreNome;
    private String email;
    private DocumentoCanonico documento;
    private EnderecoCanonico endereco;
    private List<TelefoneCanonico> telefones;
    private AcessoCanonico acesso;
    private PerfilCanonico perfil;


}
