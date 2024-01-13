package com.map.invest.mapInvest.canonicoFactory;

import com.map.invest.mapInvest.canonico.AcessoCanonico;
import com.map.invest.mapInvest.entity.Acesso;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AcessoCanonicoFactory {

    public AcessoCanonico builderAcesso(Acesso acesso){
        return Optional.ofNullable(acesso).map(entidade -> {
            return AcessoCanonico.builder()
                    .acessoID(entidade.getAcessoID())
                    .usuarioID(entidade.getUsuarioID())
                    .login(entidade.getLogin())
                    .senha(entidade.getSenha())
                    .build();
        }).orElse(null);
    }
}
