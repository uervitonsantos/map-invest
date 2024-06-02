package com.map.invest.mapInvest.dtoFactory;

import com.map.invest.mapInvest.canonico.DocumentoCanonico;
import com.map.invest.mapInvest.dto.DocumentoDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DocumentoDTOFactory {

    public DocumentoDTO documentoDto(DocumentoCanonico documento) {
        return Optional.ofNullable(documento).map(canonico -> {
            return DocumentoDTO.builder()
                    .documentoID(canonico.getDocumentoID())
                    .tipoDocumento(canonico.getTipoDocumento())
                    .numeroDocumento(canonico.getNumeroDocumento())
                    .build();
        }).orElse(null);
    }

    public List<DocumentoDTO> documentosDto(List<DocumentoCanonico> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> documentoDto(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());
    }

    public DocumentoCanonico documentoCanonico(DocumentoDTO dto) {
        return Optional.ofNullable(dto).map(entidade -> {
            return DocumentoCanonico.builder()
                    .documentoID(entidade.getDocumentoID())
                    .tipoDocumento(entidade.getTipoDocumento())
                    .numeroDocumento(entidade.getNumeroDocumento())
                    .build();
        }).orElse(null);
    }
}
