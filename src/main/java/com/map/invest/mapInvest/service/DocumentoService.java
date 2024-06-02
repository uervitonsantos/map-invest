package com.map.invest.mapInvest.service;

import com.google.common.base.Strings;
import com.map.invest.mapInvest.canonico.DocumentoCanonico;
import com.map.invest.mapInvest.canonico.UsuarioCanonico;
import com.map.invest.mapInvest.entity.Documento;
import com.map.invest.mapInvest.repository.DocumentoRepositorio;
import com.map.invest.mapInvest.util.constantes.TipoDocumentoEnum;
import com.map.invest.mapInvest.util.formatacao.FormatacaoTipoDocumento;
import com.map.invest.mapInvest.util.validacao.MapInvestMensagens;
import com.map.invest.mapInvest.util.validacao.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepositorio documentoRepositorio;

    @Autowired
    private FormatacaoTipoDocumento formatacao;

    public void validaNumDocumento(UsuarioCanonico usuario) {
        Documento documento = documentoRepositorio.buscarDocumentoPorNumero(usuario.getDocumento().getNumeroDocumento());
        if (documento != null) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NUM_DOCUMENTO_JA_EXISTE.getValor());
        }
    }

    public void validaDadosDocumento(DocumentoCanonico documentoCanonico) {
        if (documentoCanonico == null) {
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_DOCUMENTO_OBRIGATORIO.getValor());
        } else {
            TipoDocumentoEnum tipo = documentoCanonico.getTipoDocumento();
            if (tipo == null) {
                throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_TIPO_DOCUMENTO_OBRIGATORIO.getValor());
            }
            if (!tipo.equals(TipoDocumentoEnum.CPF) && !tipo.equals(TipoDocumentoEnum.CNPJ)) {
                throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_TIPO_DOCUMENTO_INVALIDO.getValor());
            }
            if (Strings.isNullOrEmpty(documentoCanonico.getNumeroDocumento())) {
                throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NUMERO_DOCUMENTO_OBRIGATORIO.getValor());
            }
            if(tipo.equals(TipoDocumentoEnum.CPF) && !formatacao.validarCPF(documentoCanonico.getNumeroDocumento())){
                throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NUMERO_DOCUMENTO_CPF_INVALIDO.getValor());
            }
            if(tipo.equals(TipoDocumentoEnum.CNPJ) && !formatacao.validarCNPJ(documentoCanonico.getNumeroDocumento())){
                throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_NUMERO_DOCUMENTO_CNPJ_INVALIDO.getValor());
            }
        }
    }
}
