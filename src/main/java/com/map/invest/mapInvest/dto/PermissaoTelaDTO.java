package com.map.invest.mapInvest.dto;

public class PermissaoTelaDTO {

    private Long permissaoTelaID;
    private String nomePermissao;
    private String descricao;

    public Long getPermissaoTelaID() {
        return permissaoTelaID;
    }

    public void setPermissaoTelaID(Long permissaoTelaID) {
        this.permissaoTelaID = permissaoTelaID;
    }

    public String getNomePermissao() {
        return nomePermissao;
    }

    public void setNomePermissao(String nomePermissao) {
        this.nomePermissao = nomePermissao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
