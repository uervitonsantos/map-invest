package com.map.invest.repository;

import com.map.invest.canonico.DocumentoPrincipalCanonico;
import com.map.invest.canonicoFactory.DocumentoPrincipalCanonicoFactory;
import com.map.invest.entity.DocumentoPrincipal;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DocumentoPrincipalRepositorio extends MapInvestRepositorio {

    @Autowired
    private DocumentoPrincipalCanonicoFactory documentoPrincipalCanonicoFactory;

    public DocumentoPrincipal busca(Long documentoPrincipalID) {
        return busca(DocumentoPrincipal.class, documentoPrincipalID);
    }

    public DocumentoPrincipalCanonico buscaDocumento(Long documentoPrincipalID) {
        DocumentoPrincipal documentoPrincipal = busca(documentoPrincipalID);
        return Optional.ofNullable(documentoPrincipal).map(d -> {
            return documentoPrincipalCanonicoFactory.builderDocumento(d);
        }).orElse(null);
    }

    public DocumentoPrincipal buscarDocumentoPorNumero(String numeroDocumentoPrincipal) {
        TypedQuery<DocumentoPrincipal> query = getEntityManager().createNamedQuery("DocumentoPrincipal.buscaPorNumeroDocumentoPrincipal", DocumentoPrincipal.class);
        query.setParameter("pnumeroDocumentoPrincipal", numeroDocumentoPrincipal);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
