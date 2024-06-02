package com.map.invest.mapInvest.service;

import com.google.common.base.Strings;
import com.map.invest.mapInvest.canonico.EnderecoCanonico;
import com.map.invest.mapInvest.util.constantes.TipoEnderecoEnum;
import com.map.invest.mapInvest.util.validacao.MapInvestMensagens;
import com.map.invest.mapInvest.util.validacao.exception.ValidacaoException;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    public void validaDadosEndereco(EnderecoCanonico enderecoCanonico) {
        if (enderecoCanonico == null) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_ENDERECO_OBRIGATORIO.getValor());
        }
        TipoEnderecoEnum tipo = enderecoCanonico.getTipoEndereco();
        if (tipo == null) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_TIPO_ENDERECO_OBRIGATORIO.getValor());
        } else {
            if (!tipo.equals(TipoEnderecoEnum.RESIDENCIAL) && !tipo.equals(TipoEnderecoEnum.COMERCIAL)) {
                throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_TIPO_ENDERECO_INVALIDO.getValor());
            }
        }
        if (Strings.isNullOrEmpty(enderecoCanonico.getCep())) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_CEP_OBRIGATORIO.getValor());
        }
        if (Strings.isNullOrEmpty(enderecoCanonico.getRua())) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_RUA_OBRIGATORIO.getValor());
        }
        if (Strings.isNullOrEmpty(enderecoCanonico.getNumero())) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NUMERO_OBRIGATORIO.getValor());
        }
        if (Strings.isNullOrEmpty(enderecoCanonico.getComplemento())) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_COMPLEMENTO_OBRIGATORIO.getValor());
        }
        if (Strings.isNullOrEmpty(enderecoCanonico.getCidade())) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_CIDADE_OBRIGATORIO.getValor());
        }
        if (Strings.isNullOrEmpty(enderecoCanonico.getUf())) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_UF_OBRIGATORIO.getValor());
        }
    }

}
