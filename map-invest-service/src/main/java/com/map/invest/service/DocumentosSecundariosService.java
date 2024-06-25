package com.map.invest.service;

import com.map.invest.canonico.DocumentosSecundariosCanonico;
import com.map.invest.entity.DocumentosSecundarios;
import com.map.invest.entity.PessoaFisica;
import com.map.invest.repository.DocumentosSecundariosRepositorio;
import com.map.invest.util.validacao.MapInvestMensagens;
import com.map.invest.util.validacao.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DocumentosSecundariosService {

    @Autowired
    private DocumentosSecundariosRepositorio documentosSecundariosRepositorio;

    private DocumentosSecundarios buscarDocumentosSecundarios(Long documentosSecundarioslID) {
        return Optional.ofNullable(documentosSecundariosRepositorio.busca(DocumentosSecundarios.class, documentosSecundarioslID))
                .orElseThrow(() -> new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_COD_DOCUMENTO_NAO_EXISTE.getValor()));
    }

    public void popularDocumentosSecundarios(PessoaFisica pessoaFisica, List<DocumentosSecundariosCanonico> documentosSecundariosCanonicos) {
        List<DocumentosSecundarios> documentosSecundariosExistentes = new ArrayList<>(pessoaFisica.getDocumentosSecundarios());

        Map<Long, DocumentosSecundarios> mapaDocumentosSecundariosExistentes = documentosSecundariosExistentes.stream()
                .collect(Collectors.toMap(DocumentosSecundarios::getDocumentosSecundarioslID, secundario -> secundario));

        Set<Long> idsDocumentosSecundariosCanonicos = documentosSecundariosCanonicos.stream()
                .map(DocumentosSecundariosCanonico::getDocumentosSecundarioslID).collect(Collectors.toSet());

        for (DocumentosSecundariosCanonico documentosSecundariosCanonico : documentosSecundariosCanonicos) {
            DocumentosSecundarios documentosSecundariosExistente = mapaDocumentosSecundariosExistentes.get(documentosSecundariosCanonico.getDocumentosSecundarioslID());
            if (documentosSecundariosExistente != null) {
                documentosSecundariosExistente.setTipoDocumentosSecundarios(documentosSecundariosCanonico.getTipoDocumentosSecundarios());
                documentosSecundariosExistente.setNumeroDocumentosSecundarios(documentosSecundariosCanonico.getNumeroDocumentosSecundarios());
                documentosSecundariosExistente.setOrgaoEmissor(documentosSecundariosCanonico.getOrgaoEmissor());
                documentosSecundariosExistente.setDataEmissao(documentosSecundariosCanonico.getDataEmissao());
            } else {
                DocumentosSecundarios novoDocumentosSecundario = new DocumentosSecundarios();
                novoDocumentosSecundario.setPessoaFisicaID(pessoaFisica.getPessoaFisicaID());
                novoDocumentosSecundario.setTipoDocumentosSecundarios(documentosSecundariosCanonico.getTipoDocumentosSecundarios());
                novoDocumentosSecundario.setNumeroDocumentosSecundarios(documentosSecundariosCanonico.getNumeroDocumentosSecundarios());
                novoDocumentosSecundario.setOrgaoEmissor(documentosSecundariosCanonico.getOrgaoEmissor());
                novoDocumentosSecundario.setDataEmissao(documentosSecundariosCanonico.getDataEmissao());
                documentosSecundariosExistentes.add(novoDocumentosSecundario);
            }
        }

        List<Long> idsDocumentosSecundariosRemovidos = new ArrayList<>();

        documentosSecundariosExistentes.removeIf(documentos -> {
            if (!idsDocumentosSecundariosCanonicos.contains(documentos.getDocumentosSecundarioslID())) {
                idsDocumentosSecundariosRemovidos.add(documentos.getDocumentosSecundarioslID());
                return true;
            }
            return false;
        });

        if (!idsDocumentosSecundariosRemovidos.isEmpty()) {
            pessoaFisica.getDocumentosSecundarios().clear();
            idsDocumentosSecundariosRemovidos.forEach(documentosSecundarioslID -> {
                removeDocumentosSecundarios(documentosSecundarioslID);
            });
        } else {
            pessoaFisica.setDocumentosSecundarios(documentosSecundariosExistentes);
        }
    }

    public void validaDadosDocumentosSecundarios(DocumentosSecundariosCanonico documentosSecundarios) {
        if(documentosSecundarios == null){
            throw new ValidacaoException(MapInvestMensagens.ERRO_VALIDACAO_DOCUMENTOS_PESSOA_FISICA_OBRIGATORIO.getValor());
        }
    }

    public void removeDocumentosSecundarios(Long documentosSecundarioslID) {
        DocumentosSecundarios documento = buscarDocumentosSecundarios(documentosSecundarioslID);
        documentosSecundariosRepositorio.remove(documento);
    }
}

