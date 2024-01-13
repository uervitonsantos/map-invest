package com.map.invest.mapInvest.metamodel;

import com.map.invest.mapInvest.entity.Usuario;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Usuario.class)
public class Usuario_ {

    public static SingularAttribute<Usuario, Long> usuarioID;
    public static SingularAttribute<Usuario, Long> perfilID;
    public static SingularAttribute<Usuario, String> nome;
    public static SingularAttribute<Usuario, String> sobreNome;
    public static SingularAttribute<Usuario, String> cpfcnpj;
    public static SingularAttribute<Usuario, String> email;
    public static SingularAttribute<Usuario, String> login;
    public static SingularAttribute<Usuario, String> senha;

}
