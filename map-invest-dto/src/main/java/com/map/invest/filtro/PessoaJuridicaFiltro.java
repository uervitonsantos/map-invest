package com.map.invest.filtro;

import com.map.invest.dto.DocumentoPrincipalDTO;
import com.map.invest.util.constantes.TipoDocumentoEnum;

public class PessoaJuridicaFiltro implements Filtro {

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return true; // Inclui se for null
        if (obj instanceof DocumentoPrincipalDTO dto) {
            return dto.getTipoDocumentoPrincipal() == TipoDocumentoEnum.CPF;
        }
        return false;
    }
}
