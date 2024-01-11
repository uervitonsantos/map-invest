package com.map.invest.mapInvest.filtroBuilder;

import com.map.invest.mapInvest.filtro.UsuarioFiltro;

import java.util.List;

public class UsuarioFiltroBuilder {
    private List<Long> usuarioID;
    private Long perfilID;
    private String CodPerfil;
    private String nomePerfil;
    private String nome;
    private String sobreNome;
    private String cpfcnpj;
    private String email;
    private String login;

    public UsuarioFiltroBuilder() {
    }

    public static UsuarioFiltroBuilder newInstance() {
        return new UsuarioFiltroBuilder();
    }

    public UsuarioFiltro build() {
        UsuarioFiltro filtro = new UsuarioFiltro();
        filtro.setUsuarioID(usuarioID);
        filtro.setPerfilID(perfilID);
        filtro.setCodPerfil(CodPerfil);
        filtro.setNomePerfil(nomePerfil);
        filtro.setNome(nome);
        filtro.setSobreNome(sobreNome);
        filtro.setCpfcnpj(cpfcnpj);
        filtro.setEmail(email);
        filtro.setLogin(login);
        return filtro;
    }

    public UsuarioFiltroBuilder usuarioID(List<Long> usuarioID) {
        this.usuarioID = usuarioID;
        return this;
    }

    public UsuarioFiltroBuilder perfilID(Long perfilID) {
        this.perfilID = perfilID;
        return this;
    }

    public UsuarioFiltroBuilder CodPerfil(String CodPerfil) {
        this.CodPerfil = CodPerfil;
        return this;
    }

    public UsuarioFiltroBuilder nomePerfil(String nomePerfil) {
        this.nomePerfil = nomePerfil;
        return this;
    }

    public UsuarioFiltroBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public UsuarioFiltroBuilder sobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
        return this;
    }

    public UsuarioFiltroBuilder cpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
        return this;
    }

    public UsuarioFiltroBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UsuarioFiltroBuilder login(String login) {
        this.login = login;
        return this;
    }
}
