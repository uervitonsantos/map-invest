package com.map.invest.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "PESSOA")
@NamedQueries({@NamedQuery(name = "Pessoa.buscaPorEmail", query = "SELECT p FROM Pessoa p WHERE p.email = :pemail"),})
public class Pessoa implements Serializable {

    @Id
    @Column(name = "PESSOA_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_sequence")
    @SequenceGenerator(name = "pessoa_sequence", sequenceName = "SEQ_PESSOA", allocationSize = 1)
    private Long pessoaID;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ATIVO")
    private String ativo;

    @OneToOne(mappedBy = "pessoa", fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    private DocumentoPrincipal documentoPrincipal;

    @OneToOne(mappedBy = "pessoa", fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    private Endereco endereco;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinTable(name = "PESSOA_TELEFONE", joinColumns =
    @JoinColumn(name = "PESSOA_ID", referencedColumnName = "PESSOA_ID", insertable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "TELEFONE_PESSOA_ID", referencedColumnName = "TELEFONE_ID", insertable = false, updatable = false))
    private List<Telefone> telefones;

    @OneToOne(mappedBy = "pessoa", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    private Acesso acesso;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa")
    private List<Auditoria> auditoria;

    public void retemTelefone(List<Telefone> telefones) {
        if (telefones == null) {
            return;
        }
        this.telefones.retainAll(telefones);
    }

    public Telefone novoTelefone(Long telefoneID) {
        Telefone novo = new Telefone();
        novo.setPessoaID(pessoaID);
        novo.setTelefoneID(telefoneID);
        Telefone telefone = telefones.stream().filter(el -> el.equals(novo)).findFirst().orElse(novo);
        telefones.add(telefone);
        return telefone;
    }
}