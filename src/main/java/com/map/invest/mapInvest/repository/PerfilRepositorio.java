package com.map.invest.mapInvest.repository;

import com.map.invest.mapInvest.canonico.DocumentoCanonico;
import com.map.invest.mapInvest.canonico.PerfilCanonico;
import com.map.invest.mapInvest.canonicoFactory.PerfilCanonicoFactory;
import com.map.invest.mapInvest.entity.Documento;
import com.map.invest.mapInvest.entity.Perfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PerfilRepositorio extends MapInvestRepositorio {

    @Autowired
    private PerfilCanonicoFactory perfilCanonicoFactory;

    public Perfil busca(Long perfilID) {
        return busca(Perfil.class, perfilID);
    }

    public PerfilCanonico buscaPerfil(Long perfilID) {
        Perfil perfil = busca(perfilID);
        return Optional.ofNullable(perfil).map(p -> {
            return perfilCanonicoFactory.builderPerfil(p);
        }).orElse(null);
    }
}
