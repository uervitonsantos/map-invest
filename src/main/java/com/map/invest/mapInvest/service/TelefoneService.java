package com.map.invest.mapInvest.service;

import com.google.common.base.Strings;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.map.invest.mapInvest.canonico.TelefoneCanonico;
import com.map.invest.mapInvest.entity.Telefone;
import com.map.invest.mapInvest.repository.TelefoneRepositorio;
import com.map.invest.mapInvest.util.constantes.TipoTelefoneEnum;
import com.map.invest.mapInvest.util.validacao.MapInvestMensagens;
import com.map.invest.mapInvest.util.validacao.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TelefoneService {
    private static final int MAX_TAMANHO_CODIGO_TELEFONE = 2;
    @Autowired
    private TelefoneRepositorio telefoneRepositorio;

    public Telefone buscarTelefone(Long telefoneID) {
        return Optional.ofNullable(telefoneRepositorio.busca(Telefone.class, telefoneID))
                .orElseThrow(() -> new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_COD_TELEFONE_NAO_EXISTE.getValor()));
    }

    public void removeTelefone(Long telefoneID) {
        Telefone telefone = buscarTelefone(telefoneID);
        telefoneRepositorio.remove(telefone);
    }

    public void validaDadosTelefone(TelefoneCanonico telefones) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber phoneNumber = null;

        if (telefones == null) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_TELEFONE_OBRIGATORIO.getValor());
        }
        if (Strings.isNullOrEmpty(telefones.getCodigo())) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_CODIGO_TELEFONE_OBRIGATORIO.getValor());
        } else {
            if (!Strings.isNullOrEmpty(telefones.getCodigo()) && telefones.getCodigo().length() > MAX_TAMANHO_CODIGO_TELEFONE) {
                throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_CODIGO_TELEFONE_TAMANHO_EXCEDIDO.getValor());
            }
        }
        if (telefones.getTipoTelefone() == null) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_TIPO_TELEFONE_OBRIGATORIO.getValor());
        } else {
            try {
                TipoTelefoneEnum tipoTelefoneEnum = TipoTelefoneEnum.findByCodigo(telefones.getTipoTelefone().getValor());
                if (tipoTelefoneEnum == null) {
                    throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_TIPO_TELEFONE_INVALIDO.getValor());
                }
            } catch (NullPointerException e) {
                throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_TIPO_TELEFONE_INVALIDO.getValor());
            }
            if (Strings.isNullOrEmpty(telefones.getNumeroTelefone())) {
                throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NUMERO_TELEFONE_OBRIGATORIO.getValor());
            }

            if (telefones.getTipoTelefone().equals(TipoTelefoneEnum.RESIDENCIAL) || telefones.getTipoTelefone().equals(TipoTelefoneEnum.COMERCIAL)) {
                try {
                    phoneNumber = phoneUtil.parse(telefones.getNumeroTelefone(), "BR");
                    if (!phoneUtil.isValidNumber(phoneNumber)) {
                        throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NUMERO_TELEFONE_FIXO_INVALIDO.getValor());
                    }
                } catch (NumberParseException e) {
                    throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NUMERO_TELEFONE_FIXO_INVALIDO.getValor());
                }
            }
            if (telefones.getTipoTelefone().equals(TipoTelefoneEnum.CELULAR)) {
                try {
                    phoneNumber = phoneUtil.parse(telefones.getNumeroTelefone(), "BR");
                    if (!phoneUtil.isValidNumber(phoneNumber)) {
                        throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NUMERO_TELEFONE_CELULAR_INVALIDO.getValor());
                    }
                } catch (NumberParseException e) {
                    throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NUMERO_TELEFONE_CELULAR_INVALIDO.getValor());
                }
            }
        }
    }
}
