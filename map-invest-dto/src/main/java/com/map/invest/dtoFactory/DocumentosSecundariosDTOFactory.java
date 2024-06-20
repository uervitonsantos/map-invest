package com.map.invest.dtoFactory;

import com.map.invest.canonico.DocumentosSecundariosCanonico;
import com.map.invest.dto.DocumentosSecundariosDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DocumentosSecundariosDTOFactory {

    public DocumentosSecundariosDTO documentoSecundarioDto(DocumentosSecundariosCanonico secundarios) {
        return Optional.ofNullable(secundarios).map(entidade -> {
            return DocumentosSecundariosDTO.builder()
                    .documentosSecundarioslID(entidade.getDocumentosSecundarioslID())
                    .tipoDocumentosSecundarios(entidade.getTipoDocumentosSecundarios())
                    .numeroDocumentosSecundarios(entidade.getNumeroDocumentosSecundarios())
                    .orgaoEmissor(entidade.getOrgaoEmissor())
                    .dataEmissao(entidade.getDataEmissao())
                    .build();
        }).orElse(null);
    }

    public List<DocumentosSecundariosDTO> documentosSecundariosDto(List<DocumentosSecundariosCanonico> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> documentoSecundarioDto(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());
    }

    public DocumentosSecundariosCanonico documentosSecundariosCanonico(DocumentosSecundariosDTO dto) {
        return Optional.ofNullable(dto).map(entidade -> {
            return DocumentosSecundariosCanonico.builder()
                    .documentosSecundarioslID(entidade.getDocumentosSecundarioslID())
                    .tipoDocumentosSecundarios(entidade.getTipoDocumentosSecundarios())
                    .numeroDocumentosSecundarios(entidade.getNumeroDocumentosSecundarios())
                    .orgaoEmissor(entidade.getOrgaoEmissor())
                    .dataEmissao(entidade.getDataEmissao())
                    .build();
        }).orElse(null);
    }

    public List<DocumentosSecundariosCanonico> documentosSecundariosCanonico(List<DocumentosSecundariosDTO> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> documentosSecundariosCanonico(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());
    }
}
