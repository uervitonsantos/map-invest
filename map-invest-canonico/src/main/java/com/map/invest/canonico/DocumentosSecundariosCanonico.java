package com.map.invest.canonico;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.map.invest.util.constantes.TipoDocumentoSecundariosEnum;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentosSecundariosCanonico {

    private Long documentosSecundarioslID;
    private Long pessoaFisicaID;
    private TipoDocumentoSecundariosEnum tipoDocumentosSecundarios;
    private String numeroDocumentosSecundarios;
    private String orgaoEmissor;
    private LocalDate dataEmissao;

}
