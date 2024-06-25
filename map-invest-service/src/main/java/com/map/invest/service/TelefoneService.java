package com.map.invest.service;

import com.google.common.base.Strings;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.map.invest.entity.Pessoa;
import com.map.invest.entity.Telefone;
import com.map.invest.canonico.TelefoneCanonico;
import com.map.invest.repository.TelefoneRepositorio;
import com.map.invest.util.constantes.TipoTelefoneEnum;
import com.map.invest.util.validacao.MapInvestMensagens;
import com.map.invest.util.validacao.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TelefoneService {
    private static final int MAX_TAMANHO_CODIGO_TELEFONE = 2;
    @Autowired
    private TelefoneRepositorio telefoneRepositorio;

    public Telefone buscarTelefone(Long telefoneID) {
        return Optional.ofNullable(telefoneRepositorio.busca(Telefone.class, telefoneID))
                .orElseThrow(() -> new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_COD_TELEFONE_NAO_EXISTE.getValor()));
    }

    public void popularTelefone(Pessoa pessoa, List<TelefoneCanonico> telefonesCanonicos) {
        List<Telefone> telefonesExistentes = new ArrayList<>(pessoa.getTelefones());

        Map<Long, Telefone> mapaTelefonesExistentes = telefonesExistentes.stream()
                .collect(Collectors.toMap(Telefone::getTelefoneID, telefone -> telefone));

        // Set de IDs de telefones canônicos para controle de exclusão
        Set<Long> idsTelefonesCanonicos = telefonesCanonicos.stream()
                .map(TelefoneCanonico::getTelefoneID).collect(Collectors.toSet());

        // Atualiza os telefones existentes ou adiciona novos
        for (TelefoneCanonico telefoneCanonico : telefonesCanonicos) {
            Telefone telefoneExistente = mapaTelefonesExistentes.get(telefoneCanonico.getTelefoneID());
            if (telefoneExistente != null) {
                // Atualiza o telefone existente
                telefoneExistente.setCodigo(telefoneCanonico.getCodigo());
                telefoneExistente.setTipoTelefone(telefoneCanonico.getTipoTelefone());
                telefoneExistente.setNumeroTelefone(telefoneCanonico.getNumeroTelefone());
            } else {
                // Adiciona novo telefone
                Telefone novoTelefone = new Telefone();
                novoTelefone.setCodigo(telefoneCanonico.getCodigo());
                novoTelefone.setTipoTelefone(telefoneCanonico.getTipoTelefone());
                novoTelefone.setNumeroTelefone(telefoneCanonico.getNumeroTelefone());
                telefonesExistentes.add(novoTelefone);
            }
        }
        // Lista para armazenar os IDs dos telefones removidos
        List<Long> idsTelefonesRemovidos = new ArrayList<>();

        // Remove os telefones que não estão mais na lista canônica e armazena os IDs dos telefones removidos
        telefonesExistentes.removeIf(telefone -> {
            if (!idsTelefonesCanonicos.contains(telefone.getTelefoneID())) {
                idsTelefonesRemovidos.add(telefone.getTelefoneID());
                return true; // Remove o telefone da lista
            }
            return false; // Mantém o telefone na lista
        });

        // Remove os telefones do banco de dados e limpa a lista de telefones
        // do usuário, se houver algum telefone removido
        if (!idsTelefonesRemovidos.isEmpty()) {
            pessoa.getTelefones().clear();
            idsTelefonesRemovidos.forEach(telefoneID -> {
                removeTelefone(telefoneID);
            });
        } else {
            // Atualiza a lista de telefones do usuário
            pessoa.setTelefones(telefonesExistentes);
        }
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
