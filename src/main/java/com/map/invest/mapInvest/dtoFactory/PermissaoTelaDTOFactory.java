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
    public PermissaoTelaDTO permissaoTelaDto(PermissaoTelaCanonico canonico) {
        if (canonico == null) {
            return null;
        }
        PermissaoTelaDTO dto = new PermissaoTelaDTO();
        dto.setPermissaoTelaID(canonico.getPermissaoTelaID());
        dto.setNomePermissao(canonico.getNomePermissao());
        dto.setDescricao(canonico.getDescricao());
        return dto;
    }

    public List<PermissaoTelaDTO> permissoesTelaDto(List<PermissaoTelaCanonico> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> permissaoTelaDto(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());
    }

    public PermissaoTelaCanonico PermissaoCanonico(PermissaoTelaDTO dto) {
        return Optional.ofNullable(dto).map(tela -> {
            PermissaoTelaCanonico canonico = new PermissaoTelaCanonico();
            canonico.setPermissaoTelaID(tela.getPermissaoTelaID());
            canonico.setNomePermissao(tela.getNomePermissao());
            canonico.setDescricao(tela.getDescricao());
            return canonico;
        }).orElse(null);
    }
}
