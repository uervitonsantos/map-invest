package com.map.invest.mapInvest.entity;

import com.map.invest.mapInvest.util.constantes.TipoDocumentoEnum;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DocumentoPrincipal.class)
public class Documento_ {

    public static SingularAttribute<DocumentoPrincipal, Long> documentoPrincipalID;
    public static SingularAttribute<DocumentoPrincipal, Long> pessoaID;
    public static SingularAttribute<DocumentoPrincipal, TipoDocumentoEnum> tipoDocumentoPrincial;
    public static SingularAttribute<DocumentoPrincipal, String> numeroDocumentoPrincial;
}
