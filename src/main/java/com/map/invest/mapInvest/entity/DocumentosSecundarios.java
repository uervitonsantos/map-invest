package com.map.invest.mapInvest.entity;

import com.map.invest.mapInvest.util.constantes.TipoDocumentoEnum;
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
@Table(name = "DOCUMENTOS_SECUNDARIOS")
public class DocumentosSecundarios implements Serializable {

    @Id
    @Column(name = "DOCUMENTOS_SECUNDARIOS_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "documentos_secundarios_sequence")
    @SequenceGenerator(name = "documentos_secundarios_sequence", sequenceName = "SEQ_DOCUMENTOS_SECUNDARIOS", allocationSize = 1)
    private Long documentosSecundarioslID;

    @Column(name = "PESSOA_FISICA_ID")
    private Long pessoaFisicaID;

    @Column(name = "TIPO_DOCUMENTOS_SECUNDARIOS")
    private String tipoDocumentosSecundarios;

    @Column(name = "NUMERO_DOCUMENTOS_SECUNDARIOS")
    private String numeroDocumentosSecundarios;

    @Column(name = "ORGAO_EMISSOR")
    private String orgaoEmissor;

    @Column(name = "DATA_EMISSAO")
    private LocalDate dataEmissao;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "PESSOA_FISICA_ID", referencedColumnName = "PESSOA_FISICA_ID", insertable = false, updatable = false)
    private PessoaFisica pessoaFisica;

}