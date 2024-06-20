package com.map.invest.canonicoFactory;

import com.map.invest.canonico.PerfilCanonico;
import com.map.invest.entity.Perfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PerfilCanonicoFactory {

    @Autowired
    private PermissaoTelaCanonicoFactory permissaoTelaCanonicoFactory;

    public PerfilCanonico builderPerfil(Perfil perfil) {
        return Optional.ofNullable(perfil).map(entidade -> {
            return PerfilCanonico.builder()
                    .perfilID(entidade.getPerfilID())
                    .codPerfil(entidade.getCodPerfil())
                    .nomePerfil(entidade.getNomePerfil())
                    .descricao(entidade.getDescricao())
                    .permissaoTelas(permissaoTelaCanonicoFactory.PermissoesTelaCanonico(entidade.getPermissaoTelas()))
                    .build();
        }).orElse(null);
    }

    public List<PerfilCanonico> perfisCanonico(Set<Perfil> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> builderPerfil(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());

    }
}
