package com.map.invest.mapInvest.canonicoFactory;

import com.map.invest.mapInvest.canonico.DocumentoCanonico;
import com.map.invest.mapInvest.entity.Documento;
import com.map.invest.mapInvest.util.constantes.TipoDocumentoEnum;
import com.map.invest.mapInvest.util.formatacao.FormatacaoTipoDocumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DocumentoCanonicoFactory {

    @Autowired
    private FormatacaoTipoDocumento formatacao;

    public DocumentoCanonico builderDocumento(Documento documento) {
        return Optional.ofNullable(documento).map(entidade -> {
            String numeroFormatado = null;
            TipoDocumentoEnum tipo = entidade.getTipoDocumento();
            if (tipo.equals(TipoDocumentoEnum.CPF)) {
                numeroFormatado = formatacao.formatarCPF(entidade.getNumeroDocumento());
            } else if (tipo.equals(TipoDocumentoEnum.CNPJ)) {
                numeroFormatado = formatacao.formatarCNPJ(entidade.getNumeroDocumento());
            }
            return DocumentoCanonico.builder()
                    .documentoID(entidade.getDocumentoID())
                    .usuarioID(entidade.getUsuarioID())
                    .tipoDocumento(entidade.getTipoDocumento())
                    .numeroDocumento(numeroFormatado)
                    .build();
        }).orElse(null);
    }
}
