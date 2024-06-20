package com.map.invest.service;

import com.google.common.base.Strings;
import com.map.invest.entity.Endereco;
import com.map.invest.canonico.EnderecoCanonico;
import com.map.invest.util.constantes.TipoEnderecoEnum;
import com.map.invest.util.validacao.MapInvestMensagens;
import com.map.invest.util.validacao.exception.ValidacaoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.regex.Pattern;

@Service
public class EnderecoService {

    private static final String REGEX_CEP = "\\d{5}-\\d{3}";

    @Value("${url.via.cep}")
    private String viaCep;
    RestClient restClient = RestClient.create();  // https://docs-spring-io.translate.goog/spring-framework/reference/integration/rest-clients.html?_x_tr_sl=en&_x_tr_tl=pt&_x_tr_hl=pt-BR&_x_tr_pto=wapp#rest-restclient

    public Endereco buscarEnderecoPorCep(String cep) {
        // Validação do formato do CEP
        if (Pattern.matches(REGEX_CEP, cep)) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_FORMATO_CEP_INVALIDO.getValor());
        }
        // Consulta ao ViaCEP
        Endereco cepResult = restClient.get()
                .uri(String.format(viaCep, cep))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_CEP_OBRIGATORIO.getValor());
                })
                .body(Endereco.class);
        return cepResult;
    }

    public void validaDadosEndereco(EnderecoCanonico enderecoCanonico, Endereco enderecoResult) {
        if (enderecoCanonico == null) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_ENDERECO_OBRIGATORIO.getValor());
        }
        TipoEnderecoEnum tipo = enderecoCanonico.getTipoEndereco();
        if (tipo == null) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_TIPO_ENDERECO_OBRIGATORIO.getValor());
        } else {
            if (!tipo.equals(TipoEnderecoEnum.RESIDENCIAL) && !tipo.equals(TipoEnderecoEnum.COMERCIAL)) {
                throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_TIPO_ENDERECO_INVALIDO.getValor());
            }
        }
        if (Strings.isNullOrEmpty(enderecoCanonico.getCep())) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_CEP_OBRIGATORIO.getValor());
        }
        if (Strings.isNullOrEmpty(enderecoResult.getLogradouro())) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_RUA_OBRIGATORIO.getValor());
        }
        if (Strings.isNullOrEmpty(enderecoCanonico.getNumero())) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NUMERO_OBRIGATORIO.getValor());
        }
        if (Strings.isNullOrEmpty(enderecoCanonico.getComplemento())) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_COMPLEMENTO_OBRIGATORIO.getValor());
        }
        if (Strings.isNullOrEmpty(enderecoResult.getBairro())) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_BAIRRO_OBRIGATORIO.getValor());
        }
        if (Strings.isNullOrEmpty(enderecoResult.getLocalidade())) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_CIDADE_OBRIGATORIO.getValor());
        }
        if (Strings.isNullOrEmpty(enderecoResult.getUf())) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_UF_OBRIGATORIO.getValor());
        }
    }

}
