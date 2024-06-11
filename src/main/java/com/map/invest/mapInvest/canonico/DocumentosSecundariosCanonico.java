package com.map.invest.mapInvest.canonico;

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
    private String tipoDocumentosSecundarios;
    private String numeroDocumentosSecundarios;
    private String orgaoEmissor;
    private LocalDate dataEmissao;

}
