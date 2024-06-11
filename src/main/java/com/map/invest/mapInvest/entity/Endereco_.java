package com.map.invest.mapInvest.entity;

import com.map.invest.mapInvest.util.constantes.TipoEnderecoEnum;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Endereco.class)
public class Endereco_ {

    public static SingularAttribute<Endereco, Long> enderecoID;
    public static SingularAttribute<Endereco, Long> pessoaID;
    public static SingularAttribute<Endereco, TipoEnderecoEnum> tipoEndereco;
    public static SingularAttribute<Endereco, String> cep;
    public static SingularAttribute<Endereco, String> logradouro;
    public static SingularAttribute<Endereco, String> numero;
    public static SingularAttribute<Endereco, String> complemento;
    public static SingularAttribute<Endereco, String> bairro;
    public static SingularAttribute<Endereco, String> localidade;
    public static SingularAttribute<Endereco, String> uf;
}
