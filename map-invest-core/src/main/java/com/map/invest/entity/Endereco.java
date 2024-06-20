package com.map.invest.entity;

import com.map.invest.util.constantes.TipoEnderecoEnum;
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

    @Column(name = "PESSOA_ID")
    private Long pessoaID;

    @Column(name = "TIPO_ENDERECO")
    private TipoEnderecoEnum tipoEndereco;

    @Column(name = "CEP")
    private String cep;

    @Column(name = "RUA")
    private String logradouro;

    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "COMPLEMENTO")
    private String complemento;

    @Column(name = "BAIRRO")
    private String bairro;

    @Column(name = "CIDADE")
    private String localidade;

    @Column(name = "UF")
    private String uf;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "PESSOA_ID", referencedColumnName = "PESSOA_ID", insertable = false, updatable = false)
    private Pessoa pessoa;
}