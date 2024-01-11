package com.map.invest.mapInvest.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "PERFIL")
public class Perfil implements MapInvestEntity {

    @Id
    @Column(name = "PERFIL_ID")
    private Long perfilID;

    @Column(name = "SIG_PERFIL")
    private String codPerfil;

    @Column(name = "NOME_PERFIL")
    private String nomePerfil;

    @Column(name = "DESCRICAO")
    private String descricao;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PERFIL_PERMISSAO",
            joinColumns = @JoinColumn(name = "PERFIL_ID", referencedColumnName = "PERFIL_ID", insertable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "PERMISSAO_TELA_ID", referencedColumnName = "PERMISSAOTELA_ID", insertable = false, updatable = false))
    private List<PermissaoTela> permissaoTelas;

    public Perfil() {
    }

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

    public List<PermissaoTela> getPermissaoTelas() {
        return permissaoTelas;
    }

    public void setPermissaoTelas(List<PermissaoTela> permissaoTelas) {
        this.permissaoTelas = permissaoTelas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Perfil perfil = (Perfil) o;
        return Objects.equals(perfilID, perfil.perfilID) && Objects.equals(codPerfil, perfil.codPerfil) && Objects.equals(nomePerfil, perfil.nomePerfil) && Objects.equals(descricao, perfil.descricao) && Objects.equals(permissaoTelas, perfil.permissaoTelas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(perfilID, codPerfil, nomePerfil, descricao, permissaoTelas);
    }
}
