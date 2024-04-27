package com.map.invest.mapInvest.canonico;

import com.map.invest.mapInvest.util.constantes.TipoEnderecoEnum;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoCanonico {

    private Long enderecoID;
    private Long usuarioID;
    private TipoEnderecoEnum tipoEndereco;
    private String cep;
    private String rua;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;
}
