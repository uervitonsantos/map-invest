package com.map.invest.mapInvest.dtoFactory;

import com.map.invest.mapInvest.canonico.AuditoriaCanonico;
import com.map.invest.mapInvest.dto.AuditoriaDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AuditoriaDTOFactory {

    public AuditoriaDTO auditoriaDto(AuditoriaCanonico auditoria) {
        return Optional.ofNullable(auditoria).map(canonico -> {
            return AuditoriaDTO.builder()
                    .auditoriaID(canonico.getAuditoriaID())
                    .dataAlteracao(canonico.getDataAlteracao())
                    .evento(canonico.getEvento())
                    .nomeTabela(canonico.getNomeTabela())
                    .registroID(canonico.getRegistroID())
                    .usuarioModificador(canonico.getUsuarioModificador())
                    .dadosAntigos(canonico.getDadosAntigos())
                    .dadosNovos(canonico.getDadosNovos())
                    .build();
        }).orElse(null);
    }

    public List<AuditoriaDTO> auditoriasDto(List<AuditoriaCanonico> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> auditoriaDto(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());
    }
}
