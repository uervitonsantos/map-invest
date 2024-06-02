package com.map.invest.mapInvest.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "AUDITORIA")
@DynamicUpdate
public class Auditoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auditoria_sequence")
    @SequenceGenerator(name = "auditoria_sequence", sequenceName = "SEQ_AUDITORIA", allocationSize = 1)
    @Column(name = "AUDITORIA_ID")
    private Long auditoriaID;

    @Column(name = "DATA_HORA")
    private LocalDateTime dataAlteracao;

    @Column(name = "EVENTO")
    private String evento;

    @Column(name = "TABELA_NOME")
    private String nomeTabela;

    @Column(name = "REGISTRO_ID")
    private Long registroID;

    @Column(name = "USUARIO")
    private String usuarioModificador;

    @Type(JsonType.class)
    @Column(name = "DADOS_ANTIGOS", columnDefinition = "json")
    private JsonNode dadosAntigos;

    @Type(JsonType.class)
    @Column(name = "DADOS_NOVOS", columnDefinition = "json")
    private JsonNode dadosNovos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REGISTRO_ID", referencedColumnName = "USUARIO_ID", insertable = false, updatable = false)
    private Usuario usuario;

}
