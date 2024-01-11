package com.map.invest.mapInvest.service;

import com.map.invest.mapInvest.canonico.UsuarioCanonico;
import com.map.invest.mapInvest.entity.Usuario;
import com.map.invest.mapInvest.filtro.FiltroWrapper;
import com.map.invest.mapInvest.repository.UsuarioRepositorio;
import com.map.invest.mapInvest.util.validacao.CodigoUsuario;
import com.map.invest.mapInvest.util.validacao.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public UsuarioCanonico buscaUsuario(Long usuarioID) {
        UsuarioCanonico usuario = usuarioRepositorio.buscaUsuario(usuarioID);
        if (usuario == null) {
            throw new ValidacaoException(CodigoUsuario.ERRO_AGREGACAO_NAO_ENCONTRADO);
        }
        return usuario;
    }

    public Usuario buscarUsuario(Long usuarioID) {
        return Optional.ofNullable(usuarioRepositorio.busca(Usuario.class, usuarioID))
                .orElseThrow(() -> new ValidacaoException(CodigoUsuario.ERRO_AGREGACAO_NAO_ENCONTRADO));
    }

    public List<UsuarioCanonico> buscaUsuarios(FiltroWrapper filtro) {
        return usuarioRepositorio.buscaUsuarios(filtro);
    }

    public UsuarioCanonico criaUsuario(UsuarioCanonico usuario) {
        validaUsuario(usuario);
        Usuario codUsuario = salvaUsuario(usuario);
        return buscaUsuario(codUsuario.getUsuarioID());
    }

    public UsuarioCanonico editaUsuario(UsuarioCanonico usuario) {
        buscarUsuario(usuario.getUsuarioID());
        validaUsuario(usuario);
        Usuario getUsuario = salvaUsuario(usuario);
        return buscaUsuario(getUsuario.getUsuarioID());
    }

    private Usuario salvaUsuario(UsuarioCanonico canonico) {
        Usuario usuario = geraUsuario(canonico);
        usuario.setPerfilID(canonico.getPerfilID());
        usuario.setNome(canonico.getNome());
        usuario.setSobreNome(canonico.getSobreNome());
        usuario.setCpfcnpj(canonico.getCpfcnpj());
        usuario.setEmail(canonico.getEmail());
        usuario.setLogin(canonico.getLogin());
        Usuario usuarioSalvo = usuarioRepositorio.salvaUsuario(usuario);
        return usuarioRepositorio.merge(usuarioSalvo);
    }

    private Usuario geraUsuario(UsuarioCanonico canonico) {
        if (canonico.getUsuarioID() == null) {
            return new Usuario();
        }
        return usuarioRepositorio.busca(canonico.getUsuarioID());
    }

    private void validaUsuario(UsuarioCanonico usuario) {
        if (usuario.getUsuarioID() == null) {
            throw new ValidacaoException(CodigoUsuario.ERRO_VALIDACAO_COD_AGREGACAO_OBRIGATORIO);
        }
        if (usuario.getPerfilID() == null) {
            throw new ValidacaoException(CodigoUsuario.ERRO_VALIDACAO_DESCRICAO_OBJETIVO_AGREGACAO_OBRIGATORIO);
        }

        if (usuario.getNome() != null) {
            throw new ValidacaoException(CodigoUsuario.ERRO_VALIDACAO_DATA_FIM_AGREGACAO_MENOR_QUE_DATA_INICIO);
        }
    }

    public void removeUsuario(Long usuarioID) {
        Usuario usuario = buscarUsuario(usuarioID);
        usuarioRepositorio.remove(usuario);
    }
}
