package com.map.invest.dtoFactory;


import com.map.invest.canonico.PerfilCanonico;
import com.map.invest.dto.PerfilDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PerfilDTOFactory {

    @Autowired
    private PermissaoTelaDTOFactory permissaoTelaDTOFactory;

    public PerfilDTO perfilDto(PerfilCanonico perfil) {
        return Optional.ofNullable(perfil).map(entidade -> {
            return PerfilDTO.builder()
                    .perfilID(entidade.getPerfilID())
                    .codPerfil(entidade.getCodPerfil())
                    .nomePerfil(entidade.getNomePerfil())
                    .descricao(entidade.getDescricao())
                    .permissaoTelas(permissaoTelaDTOFactory.permissoesTelaDto(entidade.getPermissaoTelas()))
                    .build();
        }).orElse(null);
    }

    public List<PerfilDTO> perfisDto(List<PerfilCanonico> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> perfilDto(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());
    }

    public PerfilCanonico perfilCanonico(PerfilDTO dto) {
        return Optional.ofNullable(dto).map(entidade -> {
            return PerfilCanonico.builder()
                    .perfilID(entidade.getPerfilID())
                    .codPerfil(entidade.getCodPerfil())
                    .nomePerfil(entidade.getNomePerfil())
                    .descricao(entidade.getDescricao())
                    .permissaoTelas(permissaoTelaDTOFactory.permissoesTelaCanonico(entidade.getPermissaoTelas()))
                    .build();
        }).orElse(null);
    }

    public List<PerfilCanonico> perfisCanonico(List<PerfilDTO> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> perfilCanonico(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());
    }

}
