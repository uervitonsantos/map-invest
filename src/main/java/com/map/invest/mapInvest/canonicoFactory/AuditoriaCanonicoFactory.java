package com.map.invest.mapInvest.canonicoFactory;

import com.map.invest.mapInvest.canonico.AuditoriaCanonico;
import com.map.invest.mapInvest.entity.Auditoria;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AuditoriaCanonicoFactory {

    public AuditoriaCanonico builderAuditoria(Auditoria auditoria) {
        return Optional.ofNullable(auditoria).map(entidade -> {
            return AuditoriaCanonico.builder()
                    .auditoriaID(entidade.getAuditoriaID())
                    .dataAlteracao(entidade.getDataAlteracao())
                    .evento(entidade.getEvento())
                    .nomeTabela(entidade.getNomeTabela())
                    .registroID(entidade.getRegistroID())
                    .usuarioModificador(entidade.getUsuarioModificador())
                    .dadosAntigos(entidade.getDadosAntigos())
                    .dadosNovos(entidade.getDadosNovos())
                    .build();
        }).orElse(null);
    }

    public List<AuditoriaCanonico> auditoriasCanonico(List<Auditoria> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> builderAuditoria(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());

    }
}
