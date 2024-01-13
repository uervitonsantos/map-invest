package com.map.invest.mapInvest.canonicoFactory;

import com.map.invest.mapInvest.canonico.DocumentoCanonico;
import com.map.invest.mapInvest.entity.Documento;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DocumentoCanonicoFactory {

    public DocumentoCanonico builderDocumento(Documento documento) {
        return Optional.ofNullable(documento).map(entidade -> {
            return DocumentoCanonico.builder()
                    .documentoID(entidade.getDocumentoID())
                    .usuarioID(entidade.getUsuarioID())
                    .tipoDocumento(entidade.getTipoDocumento())
                    .numeroDocumento(entidade.getNumeroDocumento())
                    .build();
        }).orElse(null);
    }
}
