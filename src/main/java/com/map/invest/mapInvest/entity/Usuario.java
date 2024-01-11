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
@Table(name = "USUARIOS")
public class Usuario implements Serializable {

    @Id
    @Column(name = "USUARIO_ID")
    private Long usuarioID;

    @Id
    @Column(name = "PERFIL_ID")
    private Long perfilID;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "SOBRENOME")
    private String sobreNome;

    @Column(name = "CPF_CNPJ")
    private String cpfcnpj;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "LOGIN_USUARIO")
    private String login;

    @Column(name = "SENHA_USUARIO")
    private String senha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERFIL_ID", referencedColumnName = "PERFIL_ID", insertable = false, updatable = false)
    private Perfil perfil;

}
