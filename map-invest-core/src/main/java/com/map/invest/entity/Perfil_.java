package com.map.invest.entity;

import com.map.invest.util.constantes.TipoPerfilEnum;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Perfil.class)
public class Perfil_ {

    public static SingularAttribute<Perfil, Long> perfilID;
    public static SingularAttribute<Perfil, String> codPerfil;
    public static SingularAttribute<Perfil, TipoPerfilEnum> nomePerfil;
    public static SingularAttribute<Perfil, String> descricao;
    public static SetAttribute<Perfil, PermissaoTela> permissaoTelas;
}
