package com.map.invest.mapInvest.repository;

import com.map.invest.mapInvest.canonico.DocumentoCanonico;
import com.map.invest.mapInvest.canonicoFactory.DocumentoCanonicoFactory;
import com.map.invest.mapInvest.entity.Documento;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DocumentoRepositorio extends MapInvestRepositorio {

    @Autowired
    private DocumentoCanonicoFactory documentoCanonicoFactory;

    public Documento busca(Long documentoID) {
        return busca(Documento.class, documentoID);
    }

    public DocumentoCanonico buscaDocumento(Long documentoID) {
        Documento documento = busca(documentoID);
        return Optional.ofNullable(documento).map(d -> {
            return documentoCanonicoFactory.builderDocumento(d);
        }).orElse(null);
    }

    public Documento buscarDocumentoPorNumero(String numeroDocumento) {
        TypedQuery<Documento> query = getEntityManager().createNamedQuery("Documento.buscaPorNumeroDocumento", Documento.class);
        query.setParameter("pnumeroDocumento", numeroDocumento);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
