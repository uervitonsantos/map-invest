package com.map.invest.mapInvest.filtro;

import com.map.invest.mapInvest.dto.DocumentoPrincipalDTO;
import com.map.invest.mapInvest.util.constantes.TipoDocumentoEnum;

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
