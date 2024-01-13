package com.map.invest.mapInvest.service;

import com.google.common.base.Strings;
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
        return Optional.ofNullable(usuarioRepositorio.buscaUsuario(usuarioID))
                .orElseThrow(() -> new ValidacaoException(CodigoUsuario.ERRO_VALIDACAO_COD_USUARIO_NAO_EXISTE.getValor()));
    }

    public Usuario buscarUsuario(Long usuarioID) {
        return Optional.ofNullable(usuarioRepositorio.busca(Usuario.class, usuarioID))
                .orElseThrow(() -> new ValidacaoException(CodigoUsuario.ERRO_VALIDACAO_COD_USUARIO_NAO_EXISTE.getValor()));
    }


    public List<UsuarioCanonico> buscaUsuarios(FiltroWrapper filtro) {
        return usuarioRepositorio.buscaUsuarios(filtro);
    }

    public UsuarioCanonico criaUsuario(UsuarioCanonico usuario) {
        UsuarioCanonico user = usuarioRepositorio.buscaUsuario(usuario.getUsuarioID());
        if (user != null) {
            throw new ValidacaoException(CodigoUsuario.ERRO_VALIDACAO_COD_USUARIO_JA_EXISTE.getValor());
        }
        validaDadosUsuario(usuario);
        Long codUsuario = salvaUsuario(usuario);
        return buscaUsuario(codUsuario);
    }

    public Long editaUsuario(UsuarioCanonico usuarioCanonico) {
        Usuario usuario = buscarUsuario(usuarioCanonico.getUsuarioID());
        validaUsuario(usuario, usuarioCanonico);
        popularUsuario(usuario, usuarioCanonico);
        usuario = usuarioRepositorio.merge(usuario);
        return usuario.getUsuarioID();
    }

    private void popularUsuario(Usuario usuario, UsuarioCanonico usuarioCanonico) {
        usuario.setPerfilID(usuarioCanonico.getPerfilID());
        usuario.setNome(usuarioCanonico.getNome());
        usuario.setSobreNome(usuarioCanonico.getSobreNome());
        usuario.setEmail(usuarioCanonico.getEmail());
    }

    private Long salvaUsuario(UsuarioCanonico canonico) {
        Usuario usuario = geraUsuario(canonico);
        usuario.setPerfilID(canonico.getPerfilID());
        usuario.setNome(canonico.getNome());
        usuario.setSobreNome(canonico.getSobreNome());
        usuario.setEmail(canonico.getEmail());
        Usuario usuarioSalvo = usuarioRepositorio.salvaUsuario(usuario);
        return usuarioSalvo.getUsuarioID();
    }

    private Usuario geraUsuario(UsuarioCanonico canonico) {
        if (canonico.getUsuarioID() == null) {
            return new Usuario();
        }
        return usuarioRepositorio.busca(canonico.getUsuarioID());
    }

    private void validaUsuario(Usuario usuario, UsuarioCanonico usuarioCanonico) {
        validaDadosUsuario(usuarioCanonico);
        validaPadraoLoginSenha(usuario, usuarioCanonico);

    }

    private void validaDadosUsuario(UsuarioCanonico usuarioCanonico) {
        if (usuarioCanonico.getPerfilID() == null) {
            throw new ValidacaoException(CodigoUsuario.ERRO_VALIDACAO_COD_PERFIL_OBRIGATORIO.getValor());
        }

        if (Strings.isNullOrEmpty(usuarioCanonico.getNome())) {
            throw new ValidacaoException(CodigoUsuario.ERRO_VALIDACAO_NOME_OBRIGATORIO.getValor());
        }

        if (Strings.isNullOrEmpty(usuarioCanonico.getSobreNome())) {
            throw new ValidacaoException(CodigoUsuario.ERRO_VALIDACAO_SOBRENOME_OBRIGATORIO.getValor());
        }

        if (Strings.isNullOrEmpty(usuarioCanonico.getEmail())) {
            throw new ValidacaoException(CodigoUsuario.ERRO_VALIDACAO_EMAIL_OBRIGATORIO.getValor());
        }
    }

    private void validaPadraoLoginSenha(Usuario usuario, UsuarioCanonico usuarioCanonico) {
        //Implementar a validação de um padrão para o login e numeros de caracteres da senha
    }

    public void removeUsuario(Long usuarioID) {
        Usuario usuario = buscarUsuario(usuarioID);
        usuarioRepositorio.remove(usuario);
    }
}
