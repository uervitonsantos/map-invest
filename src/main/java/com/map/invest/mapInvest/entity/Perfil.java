package com.map.invest.mapInvest.entity;

import com.map.invest.mapInvest.util.constantes.TipoPerfilEnum;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "PERFIL")
public class Perfil implements MapInvestEntity {

    @Id
    @Column(name = "PERFIL_ID")
    private Long perfilID;

    @Column(name = "SIG_PERFIL")
    private String codPerfil;

    @Column(name = "NOME_PERFIL")
    private TipoPerfilEnum nomePerfil;

    @Column(name = "DESCRICAO")
    private String descricao;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PERFIL_PERMISSAO",
            joinColumns = @JoinColumn(name = "PERFIL_ID", referencedColumnName = "PERFIL_ID", insertable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "PERMISSAO_TELA_ID", referencedColumnName = "PERMISSAOTELA_ID", insertable = false, updatable = false))
    private List<PermissaoTela> permissaoTelas;

    @ManyToMany(mappedBy = "perfis", fetch = FetchType.LAZY)
    private List<Acesso> acessos;
}
