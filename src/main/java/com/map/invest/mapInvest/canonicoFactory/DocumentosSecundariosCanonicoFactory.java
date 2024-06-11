package com.map.invest.mapInvest.canonicoFactory;

import com.map.invest.mapInvest.canonico.DocumentosSecundariosCanonico;
import com.map.invest.mapInvest.entity.DocumentosSecundarios;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DocumentosSecundariosCanonicoFactory {

    public DocumentosSecundariosCanonico builderDocumentosSecundarios(DocumentosSecundarios secundarios) {
        return Optional.ofNullable(secundarios).map(entidade -> {
            return DocumentosSecundariosCanonico.builder()
                    .documentosSecundarioslID(entidade.getDocumentosSecundarioslID())
                    .pessoaFisicaID(entidade.getPessoaFisicaID())
                    .tipoDocumentosSecundarios(entidade.getTipoDocumentosSecundarios())
                    .numeroDocumentosSecundarios(entidade.getNumeroDocumentosSecundarios())
                    .orgaoEmissor(entidade.getOrgaoEmissor())
                    .dataEmissao(entidade.getDataEmissao())
                    .build();
        }).orElse(null);
    }

    public List<DocumentosSecundariosCanonico> documentosSecundariosCanonico(List<DocumentosSecundarios> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> builderDocumentosSecundarios(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());
    }
}
