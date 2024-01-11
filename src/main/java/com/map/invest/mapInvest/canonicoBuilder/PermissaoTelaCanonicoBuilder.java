package com.map.invest.mapInvest.canonicoBuilder;

import com.map.invest.mapInvest.canonico.PermissaoTelaCanonico;

public class PermissaoTelaCanonicoBuilder {

    private Long permissaoTelaID;
    private String nomePermissao;
    private String descricao;

    public PermissaoTelaCanonicoBuilder() {
    }

    public static PermissaoTelaCanonicoBuilder newInstance() {
        return new PermissaoTelaCanonicoBuilder();
    }

    public PermissaoTelaCanonico build() {
        PermissaoTelaCanonico canonico = new PermissaoTelaCanonico();
        canonico.setPermissaoTelaID(permissaoTelaID);
        canonico.setNomePermissao(nomePermissao);
        canonico.setDescricao(descricao);
        return canonico;
    }

    public PermissaoTelaCanonicoBuilder permissaoTelaID(Long permissaoTelaID) {
        this.permissaoTelaID = permissaoTelaID;
        return this;
    }

    public PermissaoTelaCanonicoBuilder nomePermissao(String nomePermissao) {
        this.nomePermissao = nomePermissao;
        return this;
    }

    public PermissaoTelaCanonicoBuilder descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }
}
