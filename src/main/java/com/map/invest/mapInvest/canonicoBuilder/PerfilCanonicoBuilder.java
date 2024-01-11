package com.map.invest.mapInvest.canonicoBuilder;

import com.map.invest.mapInvest.canonico.PerfilCanonico;
import com.map.invest.mapInvest.canonico.PermissaoTelaCanonico;

import java.util.List;

public class PerfilCanonicoBuilder {
    private Long perfilID;
    private String codPerfil;
    private String nomePerfil;
    private String descricao;
    private List<PermissaoTelaCanonico> permissaoTelas;

    public PerfilCanonicoBuilder() {
    }

    public static PerfilCanonicoBuilder newInstance() {
        return new PerfilCanonicoBuilder();
    }

    public PerfilCanonico build() {
        PerfilCanonico canonico = new PerfilCanonico();
        canonico.setPerfilID(perfilID);
        canonico.setCodPerfil(codPerfil);
        canonico.setNomePerfil(nomePerfil);
        canonico.setDescricao(descricao);
        canonico.setPermissaoTelas(permissaoTelas);
        return canonico;
    }

    public PerfilCanonicoBuilder perfilID(Long perfilID) {
        this.perfilID = perfilID;
        return this;
    }

    public PerfilCanonicoBuilder codPerfil(String codPerfil) {
        this.codPerfil = codPerfil;
        return this;
    }

    public PerfilCanonicoBuilder nomePerfil(String nomePerfil) {
        this.nomePerfil = nomePerfil;
        return this;
    }

    public PerfilCanonicoBuilder descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public PerfilCanonicoBuilder permissaoTelas(List<PermissaoTelaCanonico> permissaoTelas) {
        this.permissaoTelas = permissaoTelas;
        return this;
    }
}
