package com.map.invest.mapInvest.canonico;

import com.map.invest.mapInvest.util.constantes.TipoDocumentoEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentoCanonico {

    private Long documentoID;
    private Long usuarioID;
    private TipoDocumentoEnum tipoDocumento;
    private String numeroDocumento;
}
