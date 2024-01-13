package com.map.invest.mapInvest.entity;

import com.map.invest.mapInvest.util.constantes.TipoEnderecoEnum;
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
@Table(name = "ENDERECO")
public class Endereco implements Serializable {

    @Id
    @Column(name = "ENDERECO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_sequence")
    @SequenceGenerator(name = "endereco_sequence", sequenceName = "SEQ_ENDERECO", allocationSize = 1)
    private Long enderecoID;

    @Column(name = "USUARIO_ID")
    private Long usuarioID;

    @Column(name = "TIPO_ENDERECO")
    private TipoEnderecoEnum tipoEndereco;

    @Column(name = "CEP")
    private String cep;

    @Column(name = "RUA")
    private String rua;

    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "COMPLEMENTO")
    private String complemento;

    @Column(name = "CIDADE")
    private String cidade;

    @Column(name = "UF")
    private String uf;

}