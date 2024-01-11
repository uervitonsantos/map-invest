package com.map.invest.mapInvest.canonico;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

@JsonIgnoreType
public class UsuarioCanonico {

    private Long usuarioID;
    private Long perfilID;
    private String nome;
    private String sobreNome;
    private String cpfcnpj;
    private String email;
    private String login;
    private PerfilCanonico perfil;

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

    public PerfilCanonico getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilCanonico perfil) {
        this.perfil = perfil;
    }
}
