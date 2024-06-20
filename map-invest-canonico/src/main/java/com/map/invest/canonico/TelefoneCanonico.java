package com.map.invest.canonico;

import com.map.invest.util.constantes.TipoTelefoneEnum;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneCanonico {

    private Long telefoneID;
    private Long pessoaID;
    private String codigo;
    private TipoTelefoneEnum tipoTelefone;
    private String numeroTelefone;
}
