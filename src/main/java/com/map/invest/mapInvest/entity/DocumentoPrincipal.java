package com.map.invest.mapInvest.entity;

import com.map.invest.mapInvest.util.constantes.TipoDocumentoEnum;
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
@Table(name = "DOCUMENTO_PRINCIPAL")
@NamedQuery(name = "DocumentoPrincipal.buscaPorNumeroDocumentoPrincipal", query = "SELECT p FROM DocumentoPrincipal p WHERE p.numeroDocumentoPrincipal = :pnumeroDocumentoPrincipal")
public class DocumentoPrincipal implements Serializable {

    @Id
    @Column(name = "DOCUMENTO_PRINCIPAL_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "documento_principal_sequence")
    @SequenceGenerator(name = "documento_princial_sequence", sequenceName = "SEQ_DOCUMENTO_PRINCIPAL", allocationSize = 1)
    private Long documentoPrincipalID;

    @Column(name = "PESSOA_ID")
    private Long pessoaID;

    @Column(name = "TIPO_DOCUMENTO_PRINCIPAL")
    private TipoDocumentoEnum tipoDocumentoPrincipal;

    @Column(name = "NUMERO_DOCUMENTO_PRINCIPAL")
    private String numeroDocumentoPrincipal;

    @OneToOne(mappedBy = "documentoPrincipal", fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    private PessoaFisica pessoaFisica;

    @OneToOne(mappedBy = "documentoPrincipal", fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    private PessoaJuridica pessoaJuridica;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "PESSOA_ID", referencedColumnName = "PESSOA_ID", insertable = false, updatable = false)
    private Pessoa pessoa;

}