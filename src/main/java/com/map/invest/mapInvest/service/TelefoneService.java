package com.map.invest.mapInvest.service;

import com.map.invest.mapInvest.entity.Telefone;
import com.map.invest.mapInvest.repository.TelefoneRepositorio;
import com.map.invest.mapInvest.util.validacao.MapInvestMensagens;
import com.map.invest.mapInvest.util.validacao.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TelefoneService {

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


}
