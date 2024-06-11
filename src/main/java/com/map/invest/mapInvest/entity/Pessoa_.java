package com.map.invest.mapInvest.entity;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Pessoa.class)
public class Pessoa_ {

    public static SingularAttribute<Pessoa, Long> pessoaID;
    public static SingularAttribute<Pessoa, String> nome;
    public static SingularAttribute<Pessoa, String> email;
    public static SingularAttribute<Pessoa, String> ativo;
    public static SingularAttribute<Pessoa, PessoaFisica> pessoaFisica;
    public static SingularAttribute<Pessoa, PessoaJuridica> pessoaJuridica;
    public static SingularAttribute<Pessoa, DocumentoPrincipal> documentoPrincipal;
    public static SingularAttribute<Pessoa, Endereco> endereco;
    public static SetAttribute<Pessoa, Telefone> telefones;
    public static SingularAttribute<Pessoa, Acesso> acesso;
    public static SetAttribute<Pessoa, Auditoria> auditoria;

}
