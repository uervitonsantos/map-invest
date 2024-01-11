package com.map.invest.mapInvest.dto;

import java.util.List;

public class PerfilDTO {

    private Long perfilID;
    private String codPerfil;
    private String nomePerfil;
    private String descricao;
    private List<PermissaoTelaDTO> permissaoTelas;

    public Long getPerfilID() {
        return perfilID;
    }

    public void setPerfilID(Long perfilID) {
        this.perfilID = perfilID;
    }

    public String getCodPerfil() {
        return codPerfil;
    }

    public void setCodPerfil(String codPerfil) {
        this.codPerfil = codPerfil;
    }

    public String getNomePerfil() {
        return nomePerfil;
    }

    public void setNomePerfil(String nomePerfil) {
        this.nomePerfil = nomePerfil;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<PermissaoTelaDTO> getPermissaoTelas() {
        return permissaoTelas;
    }

    public void setPermissaoTelas(List<PermissaoTelaDTO> permissaoTelas) {
        this.permissaoTelas = permissaoTelas;
    }
}
