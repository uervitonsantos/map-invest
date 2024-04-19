package com.map.invest.mapInvest.repository;

import com.map.invest.mapInvest.canonico.TelefoneCanonico;
import com.map.invest.mapInvest.canonicoFactory.TelefoneCanonicoFactory;
import com.map.invest.mapInvest.entity.Telefone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TelefoneRepositorio extends MapInvestRepositorio {

    @Autowired
    private TelefoneCanonicoFactory telefoneCanonicoFactory;

    public Telefone busca(Long telefoneID) {
        return busca(Telefone.class, telefoneID);
    }

    public TelefoneCanonico buscaTelefone(Long telefoneID) {
        Telefone telefone = busca(telefoneID);
        return Optional.ofNullable(telefone).map(t -> {
            return telefoneCanonicoFactory.builderTelefone(t);
        }).orElse(null);
    }
}
