package com.map.invest.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.map.invest.util.constantes.SexoEnum;
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
@Table(name = "PESSOA_FISICA")
public class PessoaFisica implements Serializable {

    @Id
    @Column(name = "PESSOA_FISICA_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_fisica_sequence")
    @SequenceGenerator(name = "pessoa_fisica_sequence", sequenceName = "SEQ_PESSOA_FISICA", allocationSize = 1)
    private Long pessoaFisicaID;

    @Column(name = "DOCUMENTO_PRINCIPAL_ID")
    private Long documentoPrincipalID;

    @Column(name = "SOBRENOME")
    private String sobrenome;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "SEXO")
    private SexoEnum sexo;

    @Column(name = "NACIONALIDADE")
    private String nacionalidade;

    @Column(name = "NATURALIDADE")
    private String naturalidade;

    @OneToMany(mappedBy = "pessoaFisica", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    private List<DocumentosSecundarios> documentosSecundarios;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "DOCUMENTO_PRINCIPAL_ID", referencedColumnName = "DOCUMENTO_PRINCIPAL_ID", insertable = false, updatable = false)
    private DocumentoPrincipal documentoPrincipal;
}