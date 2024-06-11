package com.map.invest.mapInvest.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "PESSOA_JURIDICA")
public class PessoaJuridica implements Serializable {

    @Id
    @Column(name = "PESSOA_JURIDICA_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_juridica_sequence")
    @SequenceGenerator(name = "pessoa_juridica_sequence", sequenceName = "SEQ_PESSOA_JURIDICA", allocationSize = 1)
    private Long pessoaJuridicaID;

    @Column(name = "DOCUMENTO_PRINCIPAL_ID")
    private Long documentoPrincipalID;

    @Column(name = "NOME_COMERCIAL")
    private String nomeComercia;

    @Column(name = "DATA_CONSTITUICAO")
    private LocalDate dataConstituicao;

    @Column(name = "TIPO_INSCRICAO")
    private String tipoInscricao;

    @Column(name = "NUMERO_INSCRICAO")
    private String numeroInscricao;

    @Column(name = "NATUREZA_JURIDICA")
    private String naturazaJuridica;

    @Column(name = "RAMO_ATIVIDADE")
    private String ramoAtividade;

    @Column(name = "OBJETIVO_SOCIAL")
    private String objetivoSocial;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "DOCUMENTO_PRINCIPAL_ID", referencedColumnName = "DOCUMENTO_PRINCIPAL_ID", insertable = false, updatable = false)
    private DocumentoPrincipal documentoPrincipal;

}