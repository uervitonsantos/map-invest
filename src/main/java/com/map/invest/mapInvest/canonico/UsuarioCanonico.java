package com.map.invest.mapInvest.canonico;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.map.invest.mapInvest.entity.Acesso;
import com.map.invest.mapInvest.entity.Documento;
import com.map.invest.mapInvest.entity.Endereco;
import com.map.invest.mapInvest.entity.Telefone;
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
    private AcessoCanonico acesso;
    private List<TelefoneCanonico> telefones;
    private List<EnderecoCanonico> enderecos;
    private PerfilCanonico perfil;





}
