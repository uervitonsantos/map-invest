package com.map.invest.mapInvest.dtoFactory;

import com.map.invest.mapInvest.canonico.DocumentoCanonico;
import com.map.invest.mapInvest.dto.DocumentoDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DocumentoDTOFactory {

    public DocumentoDTO documentoDto(DocumentoCanonico documento) {
        return Optional.ofNullable(documento).map(canonico -> {
            return DocumentoDTO.builder()
                    .documentoID(canonico.getDocumentoID())
                    .usuarioID(canonico.getUsuarioID())
                    .tipoDocumento(canonico.getTipoDocumento())
                    .numeroDocumento(canonico.getNumeroDocumento())
                    .build();
        }).orElse(null);
    }
}
