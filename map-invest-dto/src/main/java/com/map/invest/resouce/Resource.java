package com.map.invest.resouce;


import com.map.invest.canonico.ArquivoCanonico;
import com.map.invest.filtro.FiltroWrapper;
import com.map.invest.filtro.Paginacao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import static jakarta.ws.rs.core.HttpHeaders.CONTENT_DISPOSITION;

public interface Resource {
    public static final String RESPONSE_OK = "200";
    public static final String RESPONSE_BAD_REQUEST = "400";
    public static final String RESPONSE_UNAUTHORIZED = "401";
    public static final String RESPONSE_FORBIDDEN = "403";
    public static final String RESPONSE_NO_CONTENT = "204";
    public static final String RESPONSE_CREATED = "201";
    public static final String RESPONSE_PARCIAL = "206";
    public static final String APPLICATION_PDF = "application/pdf";
    public static final String P_QUANTIDADE_TOTAL = "quantidadeTotal";
    public static final String ATTACHMENT = "attachment; filename=%s";
    public static final String P_QUANTIDADE_REGISTROS = "quantidadeRegistros";
    public static final String P_PAGINA = "pagina";
    public static final String P_ID_PESSOA = "pessoaID";
    public static final String P_ID_PERFIL = "perfilID";
    public static final String P_COD_PERFIL = "codPerfil";
    public static final String P_NOME_PERFIL = "nomePerfil";
    public static final String P_NOME = "nome";
    public static final String P_SOBRENOME = "sobreNome";
    public static final String P_CPFCNPJ = "cpfcnpj";
    public static final String P_EMAIL = "email";
    public static final String P_LOGIN = "login";
    public static final String P_DATA_NASCIMENTO = "dataNascimento";
    public static final String P_SEXO = "sexo";
    public static final String P_TIPO_DOCUMENTO= "tipoDocumento";
    public static final String P_NUM_DOCUMENTO= "numeroDocumento";
    public static final String P_TIPO_ENDERECO= "tipoEndereco";
    public static final String P_CEP= "cep";
    public static final String P_BAIRRO = "bairro";
    public static final String P_UF = "uf";
    public static final String P_CODIGO = "codigo";
    public static final String P_TIPO_TELEFONE = "tipoTelefone";
    public static final String P_NUM_TELEFONE = "numeroTelefone";
    public static final String REF_USUARIO_POST_REQUEST = "#/components/schemas/UsuarioPOSTRequest";
    public static final String REF_USUARIO_PUT_REQUEST = "#/components/schemas/UsuarioPUTRequest";

    default ResponseBuilder respostaPaginada(FiltroWrapper wrapper) {
        return respostaPaginada(wrapper.getPaginacaoOptional());
    }

    default ResponseBuilder respostaPaginada(Optional<Paginacao> paginacao) {
        ResponseBuilder builder = Response.ok();
        if (paginacao.isPresent()) {
            if (paginacao.get().hasMais()) {
                builder.status(Status.PARTIAL_CONTENT);
            }
            builder.header(P_QUANTIDADE_TOTAL, paginacao.get().getTotalRegistros());
        }
        return builder;
    }

    default ResponseBuilder respostaPDF(ArquivoCanonico arquivoCanonico) {
        return respostaArquivo(arquivoCanonico.getArquivo(), arquivoCanonico.getNomeArquivoCompleto());
    }

    default ResponseBuilder respostaPDF(byte[] conteudo, String nomeArquivo) {
        return respostaArquivo(conteudo, nomeArquivo + ".pdf");
    }

    default ResponseBuilder respostaXLS(byte[] conteudo, String nomeArquivo) {
        return respostaArquivo(conteudo, nomeArquivo + ".xls");
    }

    default ResponseBuilder respostaXLSX(byte[] conteudo, String nomeArquivo) {
        return respostaArquivo(conteudo, nomeArquivo + ".xlsx");
    }

    default ResponseBuilder respostaTXT(ArquivoCanonico arquivoCanonico) {
        return respostaArquivo(arquivoCanonico.getArquivo(), arquivoCanonico.getNomeArquivoCompleto());
    }

    default ResponseBuilder respostaArquivo(byte[] conteudo, String nomeArquivo) {
        return Response.ok(conteudo).header(CONTENT_DISPOSITION, String.format(ATTACHMENT, nomeArquivo));
    }

    default boolean acceptPDF(HttpServletRequest request) {
        String accept = Optional.ofNullable(request.getHeader(HttpHeaders.ACCEPT)).orElse("");
        return accept.toLowerCase().contains(APPLICATION_PDF);
    }

    default boolean acceptTXT(HttpServletRequest request) {
        String accept = Optional.ofNullable(request.getHeader(HttpHeaders.ACCEPT)).orElse("");
        return accept.toLowerCase().contains(MediaType.TEXT_PLAIN);
    }

    default Map<String, Object> getParameters(UriInfo uriInfo) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();

        Map<String, Object> parameters = new HashMap<>();
        Iterator<String> it = queryParams.keySet().iterator();
        while (it.hasNext()) {
            String theKey = it.next();
            String theKeyUpper = theKey.toUpperCase();

            parameters.put(theKeyUpper, queryParams.getFirst(theKey));
        }
        return parameters;
    }

}
