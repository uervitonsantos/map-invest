package com.map.invest.mapInvest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "USUARIO")
public class Usuario implements Serializable {

    @Id
    @Column(name = "USUARIO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_sequence")
    @SequenceGenerator(name = "usuario_sequence", sequenceName = "SEQ_USUARIO", allocationSize = 1)
    private Long usuarioID;

    @Column(name = "PERFIL_ID")
    private Long perfilID;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "SOBRENOME")
    private String sobreNome;

    @Column(name = "EMAIL")
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
            CascadeType.MERGE }, orphanRemoval = true)
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID", insertable = false, updatable = false)
    private Documento documento;

    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
            CascadeType.MERGE }, orphanRemoval = true)
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID", insertable = false, updatable = false)
    private Endereco endereco;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
            CascadeType.MERGE })
    @JoinTable(name = "USUARIO_TELEFONE",
            joinColumns = @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID", insertable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "TELEFONE_USUARIO_ID", referencedColumnName = "TELEFONE_ID", insertable = false, updatable = false))
    private List<Telefone> telefones;

    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
            CascadeType.MERGE }, orphanRemoval = true)
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID", insertable = false, updatable = false)
    private Acesso acesso;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
            CascadeType.MERGE })
    @JoinColumn(name = "PERFIL_ID", referencedColumnName = "PERFIL_ID", insertable = false, updatable = false)
    private Perfil perfil;

    public void retemTelefone(List<Telefone> telefones) {
        if(telefones == null){
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