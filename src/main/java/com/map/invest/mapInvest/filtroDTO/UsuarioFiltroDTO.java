package com.map.invest.mapInvest.filtroDTO;

import com.map.invest.mapInvest.api.resource.Resource;
import com.map.invest.mapInvest.filtro.FiltroWrapper;
import com.map.invest.mapInvest.filtro.Paginacao;
import com.map.invest.mapInvest.filtro.PaginacaoFactory;
import com.map.invest.mapInvest.filtro.UsuarioFiltro;
import com.map.invest.mapInvest.filtroBuilder.UsuarioFiltroBuilder;

import jakarta.ws.rs.QueryParam;
import java.util.List;
import java.util.Optional;

public class UsuarioFiltroDTO {

    @QueryParam(Resource.P_QUANTIDADE_REGISTROS)
    private Integer quantidadeRegistros;

    @QueryParam(Resource.P_PAGINA)
    private Integer pagina;

    @QueryParam(Resource.P_ID_USUARIO)
    private List<Long> usuarioID;
    @QueryParam(Resource.P_ID_PERFIL)
    private Long perfilID;
    @QueryParam(Resource.P_COD_PERFIL)
    private String CodPerfil;
    @QueryParam(Resource.P_NOME_PERFIL)
    private String nomePerfil;
    @QueryParam(Resource.P_NOME)
    private String nome;
    @QueryParam(Resource.P_SOBRENOME)
    private String sobreNome;
    @QueryParam(Resource.P_CPFCNPJ)
    private String cpfcnpj;
    @QueryParam(Resource.P_EMAIL)
    private String email;
    @QueryParam(Resource.P_LOGIN)
    private String login;

    public Integer getQuantidadeRegistros() {
        return quantidadeRegistros;
    }

    public void setQuantidadeRegistros(Integer quantidadeRegistros) {
        this.quantidadeRegistros = quantidadeRegistros;
    }

    public Integer getPagina() {
        return pagina;
    }

    public void setPagina(Integer pagina) {
        this.pagina = pagina;
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

    public FiltroWrapper filtroWrapper() {
        UsuarioFiltro filtro = UsuarioFiltroBuilder.newInstance()
                .usuarioID(usuarioID)
                .perfilID(perfilID)
                .nomePerfil(nomePerfil)
                .nome(nome)
                .sobreNome(sobreNome)
                .cpfcnpj(cpfcnpj)
                .email(email)
                .login(login)
                .build();
        return new FiltroWrapper(filtro, geraPaginacao());
    }

    public Optional<Paginacao> geraPaginacao() {
        return PaginacaoFactory.cria(pagina, quantidadeRegistros);
    }
}
