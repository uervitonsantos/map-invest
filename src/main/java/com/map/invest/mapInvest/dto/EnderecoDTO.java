package com.map.invest.mapInvest.dto;

import com.map.invest.mapInvest.util.constantes.TipoEnderecoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Endereco")
@XmlRootElement(name = "Endereco")
@XmlAccessorType(XmlAccessType.FIELD)
public class EnderecoDTO {

    @Schema(description = "Identificador único do endereço", example = "1")
    @XmlElement(nillable = false)
    private Long enderecoID;

    @Schema(description = "Tipo de endereço pode ser RESIDENCIAL ou COMERCIAL", example = "RESIDENCIAL")
    @XmlElement(nillable = false)
    private TipoEnderecoEnum tipoEndereco;

    @Schema(description = "CEP do endereço", example = "01310-100")
    @XmlElement(nillable = false)
    private String cep;

    @Schema(description = "Nome da rua", example = "Avenida Paulista")
    @XmlElement(nillable = false)
    private String logradouro;

    @Schema(description = "Número do endereço", example = "1000")
    @XmlElement(nillable = false)
    private String numero;

    @Schema(description = "Complemento com informações do endereço", example = "de 612 a 1510 - lado par")
    @XmlElement(nillable = false)
    private String complemento;

    @Schema(description = "Bairro do endereço", example = "Bela Vista")
    @XmlElement(nillable = false)
    private String bairro;

    @Schema(description = "Cidade do endereço", example = "São Paulo")
    @XmlElement(nillable = false)
    private String localidade;

    @Schema(description = "Estado do endereço", example = "SP")
    @XmlElement(nillable = false)
    private String uf;
}
