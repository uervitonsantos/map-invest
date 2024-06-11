package com.map.invest.mapInvest.service;

import com.google.common.base.Strings;
import com.map.invest.mapInvest.canonico.AcessoCanonico;
import com.map.invest.mapInvest.canonico.PerfilCanonico;
import com.map.invest.mapInvest.canonico.PessoaCanonico;
import com.map.invest.mapInvest.entity.Acesso;
import com.map.invest.mapInvest.repository.AcessoRepositorio;
import com.map.invest.mapInvest.repository.PerfilRepositorio;
import com.map.invest.mapInvest.util.validacao.MapInvestMensagens;
import com.map.invest.mapInvest.util.validacao.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class AcessoService {

    private static final int MIN_TAMANHO_LOGIN = 10;
    private static final int MAX_TAMANHO_LOGIN = 20;
    private static final int MIN_TAMANHO_SENHA = 8;
    private static final int MAX_TAMANHO_SENHA = 50;
    private static final Pattern SENHA_REGEX = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[~!@#$%^&*-+])[\\w~!@#$%^&*-+]{8,}$");
    @Autowired
    private AcessoRepositorio acessoRepositorio;

    @Autowired
    private PerfilRepositorio perfilRepositorio;

    public void validaLoginPessoa(PessoaCanonico pessoa) {
        Acesso acesso = acessoRepositorio.buscarAcessoPorLogin(pessoa.getAcesso().getLogin());
        if (acesso != null) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_LOGIN_JA_EXISTE.getValor());
        }
    }

    public void validaDadosAcesso(AcessoCanonico acessoCanonico) {
        if (acessoCanonico == null) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_ACESSO_OBRIGATORIO.getValor());
        }
        if (acessoCanonico.getPerfilID() == null) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_COD_PERFIL_OBRIGATORIO.getValor());
        } else {
            PerfilCanonico perfil = perfilRepositorio.buscaPerfil(acessoCanonico.getPerfilID());
            if (perfil == null) {
                throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_COD_PERFIL_NAO_EXISTE.getValor());
            }
        }
        if (Strings.isNullOrEmpty(acessoCanonico.getLogin())) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_LOGIN_OBRIGATORIO.getValor());
        } else {
            if (acessoCanonico.getLogin().length() < MIN_TAMANHO_LOGIN) {
                throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_LOGIN_TAMANHO_MIN_INSUFICIENTE.getValor());
            }
            if (acessoCanonico.getLogin().length() > MAX_TAMANHO_LOGIN) {
                throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_LOGIN_TAMANHO_MAX_INSUFICIENTE.getValor());
            }
        }
        if (Strings.isNullOrEmpty(acessoCanonico.getSenha())) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_SENHA_OBRIGATORIA.getValor());
        } else {
            if (acessoCanonico.getSenha().length() < MIN_TAMANHO_SENHA) {
                throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_SENHA_TAMANHO_MIN_INSUFICIENTE.getValor());
            }
            if (acessoCanonico.getSenha().length() > MAX_TAMANHO_SENHA) {
                throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_SENHA_TAMANHO_MAX_INSUFICIENTE.getValor());
            }
            if (!SENHA_REGEX.matcher(acessoCanonico.getSenha()).matches()) {
                throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_SENHA_CARACTERES_INVALIDOS.getValor());
            }
        }
    }
}
