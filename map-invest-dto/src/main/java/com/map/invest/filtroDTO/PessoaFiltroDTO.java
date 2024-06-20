package com.map.invest.filtroDTO;

import com.map.invest.filtro.FiltroWrapper;
import com.map.invest.filtro.Paginacao;
import com.map.invest.filtro.PaginacaoFactory;
import com.map.invest.filtro.PessoaFiltro;
import com.map.invest.resouce.Resource;
import com.map.invest.util.adapter.LocalDateAdapter;
import com.map.invest.util.constantes.SexoEnum;
import com.map.invest.util.constantes.TipoDocumentoEnum;
import com.map.invest.util.constantes.TipoEnderecoEnum;
import com.map.invest.util.constantes.TipoTelefoneEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.ws.rs.QueryParam;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Schema(name = "Filtro", description = "Filtro para os Dados do usuário")
@XmlRootElement(name = "Filtro")
@XmlAccessorType(XmlAccessType.FIELD)
public class PessoaFiltroDTO {

    @QueryParam(Resource.P_QUANTIDADE_REGISTROS)
    @Schema(description = "Quantidade de registros", example = "10")
    @XmlElement(nillable = false)
    private Integer quantidadeRegistros;

    @QueryParam(Resource.P_PAGINA)
    @Schema(description = "Pagina", example = "1")
    @XmlElement(nillable = false)
    private Integer pagina;

    @QueryParam(Resource.P_ID_PESSOA)
    @Schema(description = "Identificador único do usuário", example = "1")
    @XmlElement(nillable = false)
    private List<Long> pessoaID;

    @QueryParam(Resource.P_NOME)
    @Schema(description = "Nome do usuário", example = "Ariano")
    @XmlElement(nillable = false)
    private String nome;

    @QueryParam(Resource.P_SOBRENOME)
    @Schema(description = "Sobrenome do usuário", example = "Vilar Suassuna")
    @XmlElement(nillable = false)
    private String sobreNome;

    @QueryParam(Resource.P_DATA_NASCIMENTO)
    @Schema(description = "Data de nascimento do usuário", example = "1927-06-16")
    @XmlElement(nillable = false)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dataNascimento;

    @QueryParam(Resource.P_SEXO)
    @Schema(enumAsRef = true, description = "Sexo do usuário pode ser M, F ou O", example = "M")
    @XmlElement(nillable = false)
    private SexoEnum sexo;

    @QueryParam(Resource.P_EMAIL)
    @Schema(description = "Email do usuário", example = "ariano.suassuna@example.com")
    @XmlElement(nillable = false)
    private String email;

    @QueryParam(Resource.P_TIPO_DOCUMENTO)
    @Schema(description = "Tipo do documento pode ser CPF ou CNPJ", example = "CPF")
    @XmlElement(nillable = false)
    private TipoDocumentoEnum tipoDocumento;

    @QueryParam(Resource.P_NUM_DOCUMENTO)
    @Schema(description = "Número do docuneto", example = "232.630.167-68")
    @XmlElement(nillable = false)
    private String numeroDocumento;

    @QueryParam(Resource.P_TIPO_ENDERECO)
    @Schema(description = "Tipo de endereço pode ser RESIDENCIAL ou COMERCIAL", example = "RESIDENCIAL")
    @XmlElement(nillable = false)
    private TipoEnderecoEnum tipoEndereco;

    @QueryParam(Resource.P_CEP)
    @Schema(description = "CEP do endereço", example = "01310-100")
    @XmlElement(nillable = false)
    private String cep;

    @QueryParam(Resource.P_BAIRRO)
    @Schema(description = "Bairro do endereço", example = "Bela Vista")
    @XmlElement(nillable = false)
    private String bairro;

    @QueryParam(Resource.P_UF)
    @Schema(description = "Estado do endereço", example = "SP")
    @XmlElement(nillable = false)
    private String uf;

    @QueryParam(Resource.P_CODIGO)
    @Schema(description = "Código identificador do telefone", example = "55")
    @XmlElement(nillable = false)
    private String codigo;

    @QueryParam(Resource.P_TIPO_TELEFONE)
    @Schema(description = "Tipo de telefone pode ser CELULAR, RESIDENCIAL ou COMERCIAL ", example = "CELULAR")
    @XmlElement(nillable = false)
    private TipoTelefoneEnum tipoTelefone;

    @QueryParam(Resource.P_NUM_TELEFONE)
    @Schema(description = "Número do telefone", example = "11987654321")
    @XmlElement(nillable = false)
    private String numeroTelefone;

    @QueryParam(Resource.P_LOGIN)
    @Schema(description = "login do usuário", example = "ariano.suassuna")
    @XmlElement(nillable = false)
    private String login;

    @QueryParam(Resource.P_COD_PERFIL)
    @Schema(description = "Código do perfil", example = "ADM")
    @XmlElement(nillable = false)
    private String codPerfil;

    @QueryParam(Resource.P_NOME_PERFIL)
    @Schema(description = "Nome do perfil", example = "ADMINISTRADOR")
    @XmlElement(nillable = false)
    private String nomePerfil;


    public FiltroWrapper filtroWrapper() {
        PessoaFiltro filtro = PessoaFiltro.builder()
                .pessoaID(pessoaID)
                .nome(nome)
                .sobreNome(sobreNome)
                .dataNascimento(dataNascimento)
                .sexo(sexo)
                .email(email)
                .tipoDocumento(tipoDocumento)
                .numeroDocumento(numeroDocumento)
                .tipoEndereco(tipoEndereco)
                .cep(cep)
                .bairro(bairro)
                .uf(uf)
                .codigo(codigo)
                .tipoTelefone(tipoTelefone)
                .numeroTelefone(numeroTelefone)
                .login(login)
                .codPerfil(codPerfil)
                .nomePerfil(nomePerfil)
                .build();
        return new FiltroWrapper(filtro, geraPaginacao());
    }

    public Optional<Paginacao> geraPaginacao() {
        return PaginacaoFactory.cria(pagina, quantidadeRegistros);
    }
}
