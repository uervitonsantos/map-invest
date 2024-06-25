package com.map.invest.canonicoFactory;

import com.map.invest.canonico.DocumentoPrincipalCanonico;
import com.map.invest.entity.DocumentoPrincipal;
import com.map.invest.util.constantes.TipoDocumentoEnum;
import com.map.invest.util.formatacao.FormatacaoTipoDocumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DocumentoPrincipalCanonicoFactory {

    @Autowired
    private FormatacaoTipoDocumento formatacao;

    @Autowired
    private PessoaFisicaCanonicoFactory pessoaFisicaCanonicoFactory;

    @Autowired
    private PessoaJuridicaCanonicoFactory pessoaJuridicaCanonicoFactory;

    public DocumentoPrincipalCanonico builderDocumento(DocumentoPrincipal documentoPrincipal) {
        return Optional.ofNullable(documentoPrincipal).map(entidade -> {
            var numeroFormatado = getString(entidade);
            return DocumentoPrincipalCanonico.builder()
                    .documentoPrincipalID(entidade.getDocumentoPrincipalID())
                    .pessoaID(entidade.getPessoaID())
                    .tipoDocumentoPrincipal(entidade.getTipoDocumentoPrincipal())
                    .numeroDocumentoPrincipal(numeroFormatado)
                    .pessoaFisica(pessoaFisicaCanonicoFactory.builderPessoaFisica(entidade.getPessoaFisica()))
                    .pessoaJuridica(pessoaJuridicaCanonicoFactory.builderPessoaJuridica(entidade.getPessoaJuridica()))
                    .build();
        }).orElse(null);
    }

    private String getString(DocumentoPrincipal entidade) {
        String numeroFormatado = null;
        TipoDocumentoEnum tipo = entidade.getTipoDocumentoPrincipal();
        if (tipo.equals(TipoDocumentoEnum.CPF)) {
            numeroFormatado = formatacao.formatarCPF(entidade.getNumeroDocumentoPrincipal());
        } else if (tipo.equals(TipoDocumentoEnum.CNPJ)) {
            numeroFormatado = formatacao.formatarCNPJ(entidade.getNumeroDocumentoPrincipal());
        }
        return numeroFormatado;
    }
}
