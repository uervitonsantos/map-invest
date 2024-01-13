package com.map.invest.mapInvest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UsuarioDTO {

    private Long usuarioID;
    private Long perfilID;
    private String nome;
    private String sobreNome;
    private String email;
    private DocumentoDTO documento;
    private EnderecoDTO endereco;
    private List<TelefoneDTO> telefones;
    private AcessoDTO acesso;
    private PerfilDTO perfil;



}
