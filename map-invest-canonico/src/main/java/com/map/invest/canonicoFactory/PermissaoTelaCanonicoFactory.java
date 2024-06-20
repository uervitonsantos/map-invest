package com.map.invest.canonicoFactory;

import com.map.invest.canonico.PermissaoTelaCanonico;
import com.map.invest.entity.PermissaoTela;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PermissaoTelaCanonicoFactory {
    public PermissaoTelaCanonico builderPermissaoTela(PermissaoTela tela) {
        return Optional.ofNullable(tela).map(entidade -> {
            return PermissaoTelaCanonico.builder()
                    .permissaoTelaID(entidade.getPermissaoTelaID())
                    .nomePermissao(entidade.getNomePermissao())
                    .descricao(entidade.getDescricao())
                    .build();
        }).orElse(null);
    }

    public List<PermissaoTelaCanonico> PermissoesTelaCanonico(List<PermissaoTela> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> builderPermissaoTela(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());

    }
}
