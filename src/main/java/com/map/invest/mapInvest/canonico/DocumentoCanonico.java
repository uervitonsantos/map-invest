package com.map.invest.mapInvest.canonico;

import com.map.invest.mapInvest.util.constantes.TipoDocumentoEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DocumentoCanonico {

    private Long documentoID;
    private Long usuarioID;
    private TipoDocumentoEnum tipoDocumento;
    private String numeroDocumento;
}
