package com.map.invest.mapInvest.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "PERMISSAOTELA")
public class PermissaoTela implements MapInvestEntity {

    @Id
    @Column(name = "PERMISSAOTELA_ID")
    private Long permissaoTelaID;

    @Column(name = "NOME_PERMISSAO")
    private String nomePermissao;

    @Column(name = "DESCRICAO")
    private String descricao;

    @ManyToMany(mappedBy = "permissaoTelas", fetch = FetchType.LAZY)
    private List<Perfil> perfils;

    public PermissaoTela() {
    }

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

    public List<Perfil> getPerfils() {
        return perfils;
    }

    public void setPerfils(List<Perfil> perfils) {
        this.perfils = perfils;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissaoTela that = (PermissaoTela) o;
        return Objects.equals(permissaoTelaID, that.permissaoTelaID) && Objects.equals(nomePermissao, that.nomePermissao) && Objects.equals(descricao, that.descricao) && Objects.equals(perfils, that.perfils);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permissaoTelaID, nomePermissao, descricao, perfils);
    }
}
