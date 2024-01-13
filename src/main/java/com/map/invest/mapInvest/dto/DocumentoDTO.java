package com.map.invest.mapInvest.dto;

import com.map.invest.mapInvest.util.constantes.TipoDocumentoEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DocumentoDTO {

    private Long documentoID;
    private Long usuarioID;
    private TipoDocumentoEnum tipoDocumento;
    private String numeroDocumento;
}
