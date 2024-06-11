package com.map.invest.mapInvest.entity;

import com.map.invest.mapInvest.util.constantes.TipoTelefoneEnum;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Telefone.class)
public class Telefone_ {

    public static SingularAttribute<Telefone, Long> telefoneID;
    public static SingularAttribute<Telefone, Long> pessoaID;
    public static SingularAttribute<Telefone, String> codigo;
    public static SingularAttribute<Telefone, TipoTelefoneEnum> tipoTelefone;
    public static SingularAttribute<Telefone, String> numeroTelefone;
}
