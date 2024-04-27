package com.map.invest.mapInvest.canonico;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCanonico {

    private Long usuarioID;
    private String nome;
    private String sobreNome;
    private LocalDate dataNascimento;
    private  String sexo;
    private String email;
    private DocumentoCanonico documento;
    private EnderecoCanonico endereco;
    private List<TelefoneCanonico> telefones;
    private AcessoCanonico acesso;

}
