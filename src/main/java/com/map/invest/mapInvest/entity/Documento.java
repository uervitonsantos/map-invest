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
@Table(name = "DOCUMENTO")
@NamedQuery(name = "Documento.buscaPorNumeroDocumento", query = "SELECT p FROM Documento p WHERE p.numeroDocumento = :pnumeroDocumento")
public class Documento implements Serializable {

    @Id
    @Column(name = "DOCUMENTO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "documento_sequence")
    @SequenceGenerator(name = "documento_sequence", sequenceName = "SEQ_DOCUMENTO", allocationSize = 1)
    private Long documentoID;

    @Column(name = "USUARIO_ID")
    private Long usuarioID;

    @Column(name = "TIPO_DOCUMENTO")
    private TipoDocumentoEnum tipoDocumento;

    @Column(name = "NUMERO_DOCUMENTO")
    private String numeroDocumento;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID", insertable = false, updatable = false)
    private Usuario usuario;

}