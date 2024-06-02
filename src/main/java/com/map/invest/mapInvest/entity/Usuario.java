package com.map.invest.mapInvest.entity;

import com.map.invest.mapInvest.util.constantes.SexoEnum;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "USUARIO")
@NamedQueries({@NamedQuery(name = "Usuario.buscaPorEmail", query = "SELECT p FROM Usuario p WHERE p.email = :pemail"),})
public class Usuario implements Serializable {

    @Id
    @Column(name = "USUARIO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_sequence")
    @SequenceGenerator(name = "usuario_sequence", sequenceName = "SEQ_USUARIO", allocationSize = 1)
    private Long usuarioID;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "SOBRENOME")
    private String sobreNome;

    @Column(name = "IDADE")
    private LocalDate dataNascimento;

    @Column(name = "SEXO")
    private SexoEnum sexo;

    @Column(name = "EMAIL")
    private String email;

    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    private Documento documento;

    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    private Endereco endereco;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinTable(name = "USUARIO_TELEFONE", joinColumns = @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID", insertable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "TELEFONE_USUARIO_ID", referencedColumnName = "TELEFONE_ID", insertable = false, updatable = false))
    private List<Telefone> telefones;

    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    private Acesso acesso;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    private List<Auditoria> auditoria;

    public void retemTelefone(List<Telefone> telefones) {
        if (telefones == null) {
            return;
        }
        this.telefones.retainAll(telefones);
    }

    public Telefone novoTelefone(Long telefoneID) {
        Telefone novo = new Telefone();
        novo.setUsuarioID(usuarioID);
        novo.setTelefoneID(telefoneID);
        Telefone telefone = telefones.stream().filter(el -> el.equals(novo)).findFirst().orElse(novo);
        telefones.add(telefone);
        return telefone;
    }
}