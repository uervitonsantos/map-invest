package com.map.invest.mapInvest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class UsuarioDTO {

    private Long usuarioID;
    private String nome;
    private String sobreNome;
    private LocalDate dataNascimento;
    private  String sexo;
    private String email;
    private DocumentoDTO documento;
    private EnderecoDTO endereco;
    private List<TelefoneDTO> telefones;
    private AcessoDTO acesso;
}
