package com.map.invest.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(PermissaoTela.class)
public class PermissaoTela_ {
    public static SingularAttribute<PermissaoTela, Long> permissaoTelaID;
    public static SingularAttribute<PermissaoTela, String> nomePermissao;
    public static SingularAttribute<PermissaoTela, String> descricao;
}
