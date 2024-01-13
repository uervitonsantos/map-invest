package com.map.invest.mapInvest.repository;

import com.map.invest.mapInvest.canonico.AcessoCanonico;
import com.map.invest.mapInvest.canonico.UsuarioCanonico;
import com.map.invest.mapInvest.canonicoFactory.AcessoCanonicoFactory;
import com.map.invest.mapInvest.entity.Acesso;
import com.map.invest.mapInvest.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AcessoRepositorio extends MapInvestRepositorio {

    @Autowired
    private AcessoCanonicoFactory acessoCanonicoFactory;

    public Acesso busca(Long acessoID) {
        return busca(Acesso.class, acessoID);
    }

    public AcessoCanonico buscaAcesso(Long acessoID) {
        Acesso acesso = busca(acessoID);
        return Optional.ofNullable(acesso).map(e -> {
            return acessoCanonicoFactory.builderAcesso(e);
        }).orElse(null);
    }
}
