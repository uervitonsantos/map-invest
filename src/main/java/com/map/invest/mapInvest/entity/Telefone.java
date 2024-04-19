package com.map.invest.mapInvest.entity;

import com.map.invest.mapInvest.util.constantes.TipoTelefoneEnum;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "TELEFONE")
public class Telefone implements Serializable {

    @Id
    @Column(name = "TELEFONE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "telefone_sequence")
    @SequenceGenerator(name = "telefone_sequence", sequenceName = "SEQ_TELEFONE", allocationSize = 1)
    private Long telefoneID;

    @Column(name = "USUARIO_ID")
    private Long usuarioID;

    @Column(name = "CODIGO")
    private String codigo;

    @Column(name = "TIPO_TELEFONE")
    private TipoTelefoneEnum tipoTelefone;

    @Column(name = "NUMERO_TELEFONE")
    private String numeroTelefone;

    @ManyToMany(mappedBy = "telefones", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE,
            CascadeType.MERGE})
    private List<Usuario> usuarios;

}