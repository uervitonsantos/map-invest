package com.map.invest.mapInvest.canonico;

import com.map.invest.mapInvest.entity.Auditoria;
import com.map.invest.mapInvest.util.constantes.SexoEnum;
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
    private SexoEnum sexo;
    private String email;
    private DocumentoCanonico documento;
    private EnderecoCanonico endereco;
    private List<TelefoneCanonico> telefones;
    private AcessoCanonico acesso;
    private List<AuditoriaCanonico> auditoria;

}
