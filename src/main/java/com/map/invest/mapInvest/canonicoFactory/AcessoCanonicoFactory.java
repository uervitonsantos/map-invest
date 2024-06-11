package com.map.invest.mapInvest.canonicoFactory;

import com.map.invest.mapInvest.canonico.AcessoCanonico;
import com.map.invest.mapInvest.entity.Acesso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AcessoCanonicoFactory {

    @Autowired
    PerfilCanonicoFactory perfilCanonicoFactory;

    public AcessoCanonico builderAcesso(Acesso acesso) {
        return Optional.ofNullable(acesso).map(entidade -> {
            return AcessoCanonico.builder()
                    .acessoID(entidade.getAcessoID())
                    .pessoaID(entidade.getPessoaID())
                    .perfilID(entidade.getPerfilID())
                    .login(entidade.getLogin())
                    .senha(entidade.getSenha())
                    .perfis(perfilCanonicoFactory.builderPerfil(entidade.getPerfis()))
                    .build();
        }).orElse(null);
    }
}
