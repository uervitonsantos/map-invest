package com.map.invest.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Acesso.class)
public class Acesso_ {

    public static SingularAttribute<Acesso, Long> acessoID;
    public static SingularAttribute<Acesso, Long> pessoaID;
    public static SingularAttribute<Acesso, Long> perfilID;
    public static SingularAttribute<Acesso, String> login;
    public static SingularAttribute<Acesso, String> senha;
    public static SingularAttribute<Acesso, Perfil> perfis;
}
