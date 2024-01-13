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
public class Acesso implements Serializable {

    @Id
    @Column(name = "ACESSO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acesso_sequence")
    @SequenceGenerator(name = "acesso_sequence", sequenceName = "SEQ_ACESSO", allocationSize = 1)
    private Long acessoID;

    @Column(name = "USUARIO_ID")
    private Long usuarioID;

    @Column(name = "LOGIN_USUARIO")
    private String login;

    @Column(name = "SENHA_USUARIO")
    private String senha;
}
