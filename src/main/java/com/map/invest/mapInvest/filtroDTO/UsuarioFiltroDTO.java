package com.map.invest.mapInvest.filtroDTO;

import com.map.invest.mapInvest.api.resource.Resource;
import com.map.invest.mapInvest.filtro.FiltroWrapper;
import com.map.invest.mapInvest.filtro.Paginacao;
import com.map.invest.mapInvest.filtro.PaginacaoFactory;
import com.map.invest.mapInvest.filtro.UsuarioFiltro;
import jakarta.ws.rs.QueryParam;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
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

    public FiltroWrapper filtroWrapper() {
        UsuarioFiltro filtro = UsuarioFiltro.builder()
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
