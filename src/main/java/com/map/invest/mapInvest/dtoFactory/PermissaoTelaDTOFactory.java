package com.map.invest.mapInvest.dtoFactory;

import com.map.invest.mapInvest.canonico.PermissaoTelaCanonico;
import com.map.invest.mapInvest.dto.PermissaoTelaDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PermissaoTelaDTOFactory {
    public PermissaoTelaDTO permissaoTelaDto(PermissaoTelaCanonico permissao) {
        return Optional.ofNullable(permissao).map(entidade -> {
            return PermissaoTelaDTO.builder()
                    .permissaoTelaID(entidade.getPermissaoTelaID())
                    .nomePermissao(entidade.getNomePermissao())
                    .descricao(entidade.getDescricao())
                    .build();
        }).orElse(null);
    }

    public List<PermissaoTelaDTO> permissoesTelaDto(List<PermissaoTelaCanonico> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> permissaoTelaDto(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());
    }

    public PermissaoTelaCanonico permissaoCanonico(PermissaoTelaDTO dto) {
        return Optional.ofNullable(dto).map(permissao -> {
            return PermissaoTelaCanonico.builder()
                    .permissaoTelaID(permissao.getPermissaoTelaID())
                    .nomePermissao(permissao.getNomePermissao())
                    .descricao(permissao.getDescricao())
                    .build();
        }).orElse(null);
    }

    public List<PermissaoTelaCanonico> permissoesTelaCanonico(List<PermissaoTelaDTO> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> permissaoCanonico(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());
    }
}
