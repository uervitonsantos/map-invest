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
    private Long pessoaID;
    private TipoEnderecoEnum tipoEndereco;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
}
