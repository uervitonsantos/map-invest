package com.map.invest.mapInvest.util.validacao;

public enum CodigoUsuario implements Codigo {

    //@formatter:off

    ERRO_VALIDACAO_DESCRICAO_AGREGACAO_OBRIGATORIO("erro.validacao.descricao.agregacao.obrigatorio"),
    ERRO_VALIDACAO_DESCRICAO_OBJETIVO_AGREGACAO_OBRIGATORIO("erro.validacao.descricao.objetivo.agregacao.obrigatorio"),
    ERRO_VALIDACAO_DATA_INICIO_AGREGACAO_OBRIGATORIO("erro.validacao.data.inicio.agregacao.obrigatorio"),
    ERRO_VALIDACAO_DATA_FIM_AGREGACAO_MENOR_QUE_DATA_INICIO("erro.validacao.data.fim.agregacao.menor.que.data.inicio"),
    ERRO_VALIDACAO_COD_AGREGACAO_OBRIGATORIO("erro.validacao.cod.agregacao.obrigatorio"),
    ERRO_VALIDACAO_COD_AGREGACAO_JA_EXISTE("erro.validacao.cod.agregacao.ja.existe"),
    ERRO_AGREGACAO_NAO_ENCONTRADO("erro.agregacao.nao.encontrado"),
    ERRO_SEQ_AGREGACAO_ESTRUTURA_INFORMACAO_NAO_INFORMADO("erro.seq.agregacao.estrutura.informacao.nao.informado"),
    ERRO_SEQ_ESTRUTURA_INFORMACAO_NAO_INFORMADO("erro.seq.estrutura.informacao.nao.informado"),
    ERRO_ESTRUTURA_INFORMACAO_NAO_ENCONTRADA("erro.estrutura.informacao.nao.encontrada"),
    ERRO_DATA_INICIAL_NAO_INFORMADO("erro.data.inicial.nao.informado"),
    ;

    private String codigo;

    private CodigoUsuario(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String getCodigo() {
        return null;
    }
}
