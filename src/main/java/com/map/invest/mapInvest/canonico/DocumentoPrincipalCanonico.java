package com.map.invest.mapInvest.canonico;

import com.map.invest.mapInvest.util.constantes.TipoDocumentoEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentoPrincipalCanonico {

    private Long documentoPrincipalID;
    private Long pessoaID;
    private TipoDocumentoEnum tipoDocumentoPrincipal;
    private String numeroDocumentoPrincipal;
    private PessoaFisicaCanonico pessoaFisica;
    private PessoaJuridicaCanonico pessoaJuridica;
}
