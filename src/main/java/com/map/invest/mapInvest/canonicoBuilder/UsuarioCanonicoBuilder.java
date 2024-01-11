package com.map.invest.mapInvest.canonicoBuilder;

import com.map.invest.mapInvest.canonico.PerfilCanonico;
import com.map.invest.mapInvest.canonico.UsuarioCanonico;

public class UsuarioCanonicoBuilder {

    private Long usuarioID;
    private Long perfilID;
    private String nome;
    private String sobreNome;
    private String cpfcnpj;
    private String email;
    private String login;
    private String senha;
    private PerfilCanonico perfil;

    public UsuarioCanonicoBuilder() {
    }

    public static UsuarioCanonicoBuilder newInstance() {
        return new UsuarioCanonicoBuilder();
    }

    public UsuarioCanonico build() {
        UsuarioCanonico canonico = new UsuarioCanonico();
        canonico.setUsuarioID(usuarioID);
        canonico.setPerfilID(perfilID);
        canonico.setNome(nome);
        canonico.setSobreNome(sobreNome);
        canonico.setCpfcnpj(cpfcnpj);
        canonico.setEmail(email);
        canonico.setLogin(login);
        canonico.setPerfil(perfil);
        return canonico;
    }

    public UsuarioCanonicoBuilder usuarioID(Long usuarioID) {
        this.usuarioID = usuarioID;
        return this;
    }

    public UsuarioCanonicoBuilder perfilID(Long perfilID) {
        this.perfilID = perfilID;
        return this;
    }

    public UsuarioCanonicoBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public UsuarioCanonicoBuilder sobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
        return this;
    }

    public UsuarioCanonicoBuilder cpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
        return this;
    }

    public UsuarioCanonicoBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UsuarioCanonicoBuilder login(String login) {
        this.login = login;
        return this;
    }

    public UsuarioCanonicoBuilder senha(String senha) {
        this.senha = senha;
        return this;
    }

    public UsuarioCanonicoBuilder perfil(PerfilCanonico perfil) {
        this.perfil = perfil;
        return this;
    }
}
