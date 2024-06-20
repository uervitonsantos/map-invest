package com.map.invest.entity;

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
@Table(name = "PERMISSAOTELA")
public class PermissaoTela implements MapInvestEntity {

    @Id
    @Column(name = "PERMISSAOTELA_ID")
    private Long permissaoTelaID;

    @Column(name = "NOME_PERMISSAO")
    private String nomePermissao;

    @Column(name = "DESCRICAO")
    private String descricao;

    @ManyToMany(mappedBy = "permissaoTelas", fetch = FetchType.LAZY)
    private List<Perfil> perfils;

}
