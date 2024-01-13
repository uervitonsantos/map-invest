package com.map.invest.mapInvest.service;

import com.google.common.base.Strings;
import com.map.invest.mapInvest.canonico.*;
import com.map.invest.mapInvest.entity.*;
import com.map.invest.mapInvest.filtro.FiltroWrapper;
import com.map.invest.mapInvest.repository.UsuarioRepositorio;
import com.map.invest.mapInvest.util.validacao.CodigoUsuario;
import com.map.invest.mapInvest.util.validacao.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Long usuarioSalvo = salvaUsuario(usuario);
        return buscaUsuario(usuarioSalvo);
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
        salvaDocumento(canonico, usuarioSalvo);
        salvaEndereco(canonico, usuarioSalvo);
        salvaTelefones(canonico, usuarioSalvo);
        salvaAcesso(canonico, usuarioSalvo);
        return usuarioSalvo.getUsuarioID();
    }

    private void salvaDocumento(UsuarioCanonico usuario, Usuario usuarioSalvo) {
        Documento documento = new Documento();
        documento.setTipoDocumento(usuario.getDocumento().getTipoDocumento());
        documento.setNumeroDocumento(usuario.getDocumento().getNumeroDocumento());
        documento.setUsuarioID(usuarioSalvo.getUsuarioID());
        usuarioRepositorio.merge(documento);
    }
    private void salvaEndereco(UsuarioCanonico usuario, Usuario usuarioSalvo) {
        Endereco endereco = new Endereco();
        endereco.setUsuarioID(usuarioSalvo.getUsuarioID());
        endereco.setTipoEndereco(usuario.getEndereco().getTipoEndereco());
        endereco.setCep(usuario.getEndereco().getCep());
        endereco.setRua(usuario.getEndereco().getRua());
        endereco.setNumero(usuario.getEndereco().getNumero());
        endereco.setComplemento(usuario.getEndereco().getComplemento());
        endereco.setCidade(usuario.getEndereco().getCidade());
        endereco.setUf(usuario.getEndereco().getUf());
        usuarioRepositorio.merge(endereco);
    }
    private void salvaTelefones(UsuarioCanonico usuario, Usuario usuarioSalvo) {
        Optional.ofNullable(usuario.getTelefones()).ifPresent(tel -> {
            persisteTelefones(usuarioSalvo, tel);
        });
    }
    private void persisteTelefones(Usuario usuarioSalvo, List<TelefoneCanonico> tel) {
        List<Telefone> telefones = tel.stream().map(telefone -> montaTelefone(usuarioSalvo, telefone))
                .collect(Collectors.toList());
        usuarioSalvo.retemTelefone(telefones);
    }
    private Telefone montaTelefone(Usuario usuarioSalvo, TelefoneCanonico telefone) {
        validaTelefone(telefone);
        Telefone t = usuarioSalvo.novoTelefone(telefone.getTelefoneID());
        t.setCodigo(telefone.getCodigo());
        t.setTipoTelefone(telefone.getTipoTelefone());
        t.setNumeroTelefone(telefone.getNumeroTelefone());
        return t;
    }
    private void salvaAcesso(UsuarioCanonico usuario, Usuario usuarioSalvo) {
        Acesso acesso = new Acesso();
        acesso.setLogin(usuario.getAcesso().getLogin());
        acesso.setSenha(usuario.getAcesso().getSenha());
        acesso.setUsuarioID(usuarioSalvo.getUsuarioID());
        usuarioRepositorio.merge(acesso);
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
    private void validaCamposEndereco(EnderecoCanonico endereco) {
    }

    private void validaTelefone(TelefoneCanonico telefone) {
    }

    private void validaDadosUsuario(UsuarioCanonico usuarioCanonico) {
        if (usuarioCanonico.getPerfilID() == null) {
            throw new ValidacaoException(CodigoUsuario.ERRO_VALIDACAO_COD_PERFIL_OBRIGATORIO.getValor());
        } //Validar se o perfilID Existe na base de dados

        if (Strings.isNullOrEmpty(usuarioCanonico.getNome())) {
            throw new ValidacaoException(CodigoUsuario.ERRO_VALIDACAO_NOME_OBRIGATORIO.getValor());
        }

        if (Strings.isNullOrEmpty(usuarioCanonico.getSobreNome())) {
            throw new ValidacaoException(CodigoUsuario.ERRO_VALIDACAO_SOBRENOME_OBRIGATORIO.getValor());
        }

        if (Strings.isNullOrEmpty(usuarioCanonico.getEmail())) {
            throw new ValidacaoException(CodigoUsuario.ERRO_VALIDACAO_EMAIL_OBRIGATORIO.getValor());
        } //Validar se o email Existe na base de dados
    }

    private void validaPadraoLoginSenha(Usuario usuario, UsuarioCanonico usuarioCanonico) {
        //Implementar a validação de um padrão para o login e numeros de caracteres da senha
    }

    public void removeUsuario(Long usuarioID) {
        Usuario usuario = buscarUsuario(usuarioID);
        usuarioRepositorio.remove(usuario);
    }
}
