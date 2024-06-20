package com.map.invest.canonico;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissaoTelaCanonico {
    private Long permissaoTelaID;
    private String nomePermissao;
    private String descricao;
}
