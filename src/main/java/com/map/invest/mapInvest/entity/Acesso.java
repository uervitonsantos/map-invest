package com.map.invest.mapInvest.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "ACESSO")
@NamedQueries({@NamedQuery(name = "Acesso.buscaPorLogin", query = "SELECT p FROM Acesso p WHERE p.login = :plogin"),})

public class Acesso implements Serializable {

    @Id
    @Column(name = "ACESSO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acesso_sequence")
    @SequenceGenerator(name = "acesso_sequence", sequenceName = "SEQ_ACESSO", allocationSize = 1)
    private Long acessoID;

    @Column(name = "USUARIO_ID")
    private Long usuarioID;

    @Column(name = "PERFIL_ID")
    private Long perfilID;

    @Column(name = "LOGIN_USUARIO")
    private String login;

    @Column(name = "SENHA_USUARIO")
    private String senha;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID", insertable = false, updatable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "PERFIL_ID", referencedColumnName = "PERFIL_ID", insertable = false, updatable = false)
    private Perfil perfis;

}

