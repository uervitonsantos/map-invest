package com.map.invest.canonico;

import com.map.invest.util.constantes.TipoPerfilEnum;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerfilCanonico {
    private Long perfilID;
    private Long acessoID;
    private String codPerfil;
    private TipoPerfilEnum nomePerfil;
    private String descricao;
    private List<PermissaoTelaCanonico> permissaoTelas;

}
