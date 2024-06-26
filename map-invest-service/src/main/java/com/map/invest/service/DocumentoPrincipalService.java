package com.map.invest.service;

import com.google.common.base.Strings;
import com.map.invest.canonico.*;
import com.map.invest.entity.DocumentoPrincipal;
import com.map.invest.entity.DocumentosSecundarios;
import com.map.invest.entity.PessoaJuridica;
import com.map.invest.repository.DocumentoPrincipalRepositorio;
import com.map.invest.util.constantes.SexoEnum;
import com.map.invest.util.constantes.TipoDocumentoEnum;
import com.map.invest.util.constantes.TipoInscricaoEnum;
import com.map.invest.util.formatacao.FormatacaoTipoDocumento;
import com.map.invest.util.validacao.MapInvestMensagens;
import com.map.invest.util.validacao.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentoPrincipalService {

    @Autowired
    private DocumentoPrincipalRepositorio documentoPrincipalRepositorio;

    @Autowired
    private FormatacaoTipoDocumento formatacao;

    @Autowired
    private DocumentosSecundariosService documentosSecundariosService;

    public void validaNumDocumentoPrincipal(PessoaCanonico pessoa) {
        DocumentoPrincipal documentoPrincipal = documentoPrincipalRepositorio.buscarDocumentoPorNumero(pessoa.getDocumentoPrincipal().getNumeroDocumentoPrincipal());
        if (documentoPrincipal != null) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NUM_DOCUMENTO_JA_EXISTE.getValor());
        }
    }

    public void validaDadosDocumentoPrincipal(DocumentoPrincipalCanonico documentoCanonico) {
        if (documentoCanonico == null) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_DOCUMENTO_OBRIGATORIO.getValor());
        } else {
            TipoDocumentoEnum tipo = documentoCanonico.getTipoDocumentoPrincipal();
            if (tipo == null) {
                throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_TIPO_DOCUMENTO_OBRIGATORIO.getValor());
            }
            if (!tipo.equals(TipoDocumentoEnum.CPF) && !tipo.equals(TipoDocumentoEnum.CNPJ)) {
                throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_TIPO_DOCUMENTO_INVALIDO.getValor());
            }
            if (Strings.isNullOrEmpty(documentoCanonico.getNumeroDocumentoPrincipal())) {
                throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NUMERO_DOCUMENTO_OBRIGATORIO.getValor());
            }
            if (documentoCanonico.getNumeroDocumentoPrincipal().length() > 20) {
                throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NUMERO_DOCUMENTO_COMPRIMENTO_MAXIMO.getValor());
            }
            if (tipo.equals(TipoDocumentoEnum.CPF) && !formatacao.validarCPF(documentoCanonico.getNumeroDocumentoPrincipal())) {
                throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NUMERO_DOCUMENTO_CPF_INVALIDO.getValor());
            }
            if (tipo.equals(TipoDocumentoEnum.CNPJ) && !formatacao.validarCNPJ(documentoCanonico.getNumeroDocumentoPrincipal())) {
                throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NUMERO_DOCUMENTO_CNPJ_INVALIDO.getValor());
            }

            if (tipo.equals(TipoDocumentoEnum.CPF)) {
                PessoaFisicaCanonico pessoaFisica = documentoCanonico.getPessoaFisica();

                if (pessoaFisica == null) {
                    throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_DADOS_PESSOA_FISICA_OBRIGATORIO.getValor());
                } else {
                    if (Strings.isNullOrEmpty(pessoaFisica.getSobrenome())) {
                        throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_SOBRENOME_OBRIGATORIO.getValor());
                    }
                    if (pessoaFisica.getSobrenome().length() > 50) {
                        throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_SOBRENOME_COMPRIMENTO_MAXIMO.getValor());
                    }
                    if (pessoaFisica.getDataNascimento() == null) {
                        throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_DATA_NASCIMENTO_OBRIGATORIO.getValor());
                    }
                    SexoEnum sexo = pessoaFisica.getSexo();
                    if (sexo == null) {
                        throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_SEXO_OBRIGATORIO.getValor());
                    }
                    if (!sexo.equals(SexoEnum.F) && !sexo.equals(SexoEnum.M) && !sexo.equals(SexoEnum.O)) {
                        throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_SEXO_INVALIDO.getValor());
                    }
                    if (pessoaFisica.getNacionalidade() == null) {
                        throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NACIONALIDADE_OBRIGATORIO.getValor());
                    }
                    if (pessoaFisica.getNacionalidade().length() > 50) {
                        throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NACIONALIDADE_COMPRIMENTO_MAXIMO.getValor());
                    }
                    if (pessoaFisica.getNaturalidade() == null) {
                        throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NATURALIDADE_OBRIGATORIO.getValor());
                    }
                    if (pessoaFisica.getNaturalidade().length() > 50) {
                        throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NATURALIDADE_COMPRIMENTO_MAXIMO.getValor());
                    }

//                    List<DocumentosSecundariosCanonico> documentosSecundariosCanonicos = pessoaFisica.getDocumentosSecundarios();
//
//                    for (DocumentosSecundariosCanonico documentosSecundarios : documentosSecundariosCanonicos) {
//                        documentosSecundariosService.validaDadosDocumentosSecundarios(documentosSecundarios);
//                    }
                }
            }

            if (tipo.equals(TipoDocumentoEnum.CNPJ)) {

                PessoaJuridicaCanonico pessoaJuridica = documentoCanonico.getPessoaJuridica();
                TipoInscricaoEnum tipoInscricao = pessoaJuridica.getTipoInscricao();

                if (pessoaJuridica == null) {
                    throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_DADOS_PESSOA_JURIDICA_OBRIGATORIO.getValor());
                } else {
                    if (Strings.isNullOrEmpty(pessoaJuridica.getNomeComercia())) {
                        throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NOME_COMERCIAL_OBRIGATORIO.getValor());
                    }
                    if (pessoaJuridica.getNomeComercia().length() > 50) {
                        throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NOMECOMERCIAL_COMPRIMENTO_MAXIMO.getValor());
                    }
                    if (pessoaJuridica.getDataConstituicao() == null) {
                        throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_DATA_CONSTITUICAO_OBRIGATORIO.getValor());
                    }
                    if(tipoInscricao == null){
                        throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_TIPO_INSCRICAO_OBRIGATORIO.getValor());
                    }
                    if (!tipoInscricao.equals(TipoInscricaoEnum.MICROEMPREENDEDOR_INDIVIDUAL) &&
                            !tipoInscricao.equals(TipoInscricaoEnum.EMPRESARIO_INDIVIDUAL) &&
                            !tipoInscricao.equals(TipoInscricaoEnum.SOCIEDADE_LTDA) &&
                            !tipoInscricao.equals(TipoInscricaoEnum.SOCIEDADE_LIMITADA_UNIPESSOAL) &&
                            !tipoInscricao.equals(TipoInscricaoEnum.SOCIEDADE_SIMPLE) &&
                            !tipoInscricao.equals(TipoInscricaoEnum.SOCIEDADE_ANONIMA)) {
                        throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_TIPO_INSCRICAO_INCORRETO.getValor());
                    }
                    if (Strings.isNullOrEmpty(pessoaJuridica.getNumeroInscricao())) {
                        throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NUMERO_INSCRICAO_OBRIGATORIO.getValor());
                    }
                    if (pessoaJuridica.getNumeroInscricao().length() > 20) {
                        throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NUMERO_INSCRICAO_COMPRIMENTO_MAXIMO.getValor());
                    }
                    if (Strings.isNullOrEmpty(pessoaJuridica.getNaturazaJuridica())) {
                        throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NATUREZA_JURIDICA_OBRIGATORIO.getValor());
                    }
                    if (Strings.isNullOrEmpty(pessoaJuridica.getRamoAtividade())) {
                        throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_RAMO_ATIVIDADE_OBRIGATORIO.getValor());
                    }
                    if (Strings.isNullOrEmpty(pessoaJuridica.getObjetivoSocial())) {
                        throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_OBJETIVO_SOCIAL_OBRIGATORIO.getValor());
                    }
                }
            }
        }
    }
}
