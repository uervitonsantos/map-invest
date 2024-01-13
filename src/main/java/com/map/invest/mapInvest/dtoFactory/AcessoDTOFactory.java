package com.map.invest.mapInvest.dtoFactory;

import com.map.invest.mapInvest.canonico.AcessoCanonico;
import com.map.invest.mapInvest.canonico.UsuarioCanonico;
import com.map.invest.mapInvest.dto.AcessoDTO;
import com.map.invest.mapInvest.dto.UsuarioDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AcessoDTOFactory {

    public AcessoDTO acessoDto(AcessoCanonico acesso){
        return Optional.ofNullable(acesso).map(canonico -> {
            return AcessoDTO.builder()
                    .acessoID(canonico.getAcessoID())
                    .login(canonico.getLogin())
                    .senha(canonico.getSenha())
                    .build();
        }).orElse(null);
    }

    public List<AcessoDTO> acessosDTO(List<AcessoCanonico> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> acessoDto(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());
    }

    public AcessoCanonico acessoCanonico(AcessoDTO dto) {
        return Optional.ofNullable(dto).map(entidade -> {
            return AcessoCanonico.builder()
                    .acessoID(entidade.getAcessoID())
                    .login(entidade.getLogin())
                    .senha(entidade.getSenha())
                    .build();
        }).orElse(null);
    }
}
