package com.map.invest.mapInvest.filtro;


import java.util.List;


public class UsuarioFiltro implements Filtro {

    private List<Long> usuarioID;
    private Long perfilID;
    private String CodPerfil;
    private String nomePerfil;
    private String nome;
    private String sobreNome;
    private String cpfcnpj;
    private String email;
    private String login;

    public UsuarioFiltro() {
    }

    public UsuarioFiltro(List<Long> usuarioID, Long perfilID, String codPerfil, String nomePerfil, String nome, String sobreNome, String cpfcnpj, String email, String login) {
        this.usuarioID = usuarioID;
        this.perfilID = perfilID;
        CodPerfil = codPerfil;
        this.nomePerfil = nomePerfil;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.cpfcnpj = cpfcnpj;
        this.email = email;
        this.login = login;
    }

    public List<Long> getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(List<Long> usuarioID) {
        this.usuarioID = usuarioID;
    }

    public Long getPerfilID() {
        return perfilID;
    }

    public void setPerfilID(Long perfilID) {
        this.perfilID = perfilID;
    }

    public String getCodPerfil() {
        return CodPerfil;
    }

    public void setCodPerfil(String codPerfil) {
        CodPerfil = codPerfil;
    }

    public String getNomePerfil() {
        return nomePerfil;
    }

    public void setNomePerfil(String nomePerfil) {
        this.nomePerfil = nomePerfil;
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

    public Boolean hasUsuarioID() {
        return usuarioID != null;
    }

    public Boolean hasPerfilID() {
        return perfilID != null;
    }

    public Boolean hasCodPerfil() {
        return CodPerfil != null && CodPerfil.isEmpty();
    }

    public Boolean hasNome() {
        return nome != null && nome.isEmpty();
    }

    public Boolean hasSobreNome() {
        return sobreNome != null && sobreNome.isEmpty();
    }

    public Boolean hasCpfcnpj() {
        return cpfcnpj != null && cpfcnpj.isEmpty();
    }

    public Boolean hasEmail() {
        return email != null && email.isEmpty();
    }

    public Boolean hasLogin() {
        return login != null && login.isEmpty();
    }
}
