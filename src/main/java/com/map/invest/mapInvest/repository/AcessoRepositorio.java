package com.map.invest.mapInvest.repository;

import com.map.invest.mapInvest.canonico.AcessoCanonico;
import com.map.invest.mapInvest.canonicoFactory.AcessoCanonicoFactory;
import com.map.invest.mapInvest.entity.Acesso;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
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

    public Acesso buscarAcessoPorLogin(String login) {
        TypedQuery<Acesso> query = getEntityManager().createNamedQuery("Acesso.buscaPorLogin", Acesso.class);
        query.setParameter("plogin", login);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
