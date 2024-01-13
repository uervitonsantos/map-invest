package com.map.invest.mapInvest.canonico;

import com.map.invest.mapInvest.util.constantes.TipoTelefoneEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TelefoneCanonico {

    private Long telefoneID;
    private String codigo;
    private TipoTelefoneEnum tipoTelefone;
    private String numeroTelefone;
}
