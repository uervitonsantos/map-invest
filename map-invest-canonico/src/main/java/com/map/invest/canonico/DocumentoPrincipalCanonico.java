package com.map.invest.canonico;

import com.map.invest.util.constantes.TipoDocumentoEnum;
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
