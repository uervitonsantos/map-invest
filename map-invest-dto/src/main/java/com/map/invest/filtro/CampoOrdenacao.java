package com.map.invest.filtro;

public enum CampoOrdenacao {

    //formatter:off
    DATA_BAIXA("dataBaixa"),
    DATA_VENCIMENTO("dataVencimento"),
    SEQ_APLICACAO("seqAplicacao"),
    COD_CERTIFICADO("codCertificado"),
    COD_CONVENIO("codConvenio"),
    DATA_PROCOTOLO("dataProtocolo"),
    COD_COBRANCA("codCobranca"),
    COD_TABUA("codTabua"),
    NUM_EVENTO("numEvento"),
    NUM_ENCA("numEnca"),
    ;
    private String valor;

    private CampoOrdenacao(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
