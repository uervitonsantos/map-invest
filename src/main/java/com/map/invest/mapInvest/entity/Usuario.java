package com.map.invest.mapInvest.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "USUARIOS")
public class Usuario implements Serializable {

    @Id
    @Column(name = "USUARIO_ID")
    private Long usuarioID;

    @Id
    @Column(name = "PERFIL_ID")
    private Long perfilID;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "SOBRENOME")
    private String sobreNome;

    @Column(name = "CPF_CNPJ")
    private String cpfcnpj;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "LOGIN_USUARIO")
    private String login;

    @Column(name = "SENHA_USUARIO")
    private String senha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERFIL_ID", referencedColumnName = "PERFIL_ID", insertable = false, updatable = false)
    private Perfil perfil;

    public Usuario() {
    }

    public Long getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Long usuarioID) {
        this.usuarioID = usuarioID;
    }

    public Long getPerfilID() {
        return perfilID;
    }

    public void setPerfilID(Long perfilID) {
        this.perfilID = perfilID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public String getCpfcnpj() {
        return cpfcnpj;
    }

    public void setCpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(usuarioID, usuario.usuarioID) && Objects.equals(perfilID, usuario.perfilID) && Objects.equals(nome, usuario.nome) && Objects.equals(sobreNome, usuario.sobreNome) && Objects.equals(cpfcnpj, usuario.cpfcnpj) && Objects.equals(email, usuario.email) && Objects.equals(login, usuario.login) && Objects.equals(senha, usuario.senha) && Objects.equals(perfil, usuario.perfil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioID, perfilID, nome, sobreNome, cpfcnpj, email, login, senha, perfil);
    }
}
